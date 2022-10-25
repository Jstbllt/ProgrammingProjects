import { auth, db } from "../firebase";
import { doc, getDoc, setDoc, updateDoc } from "firebase/firestore";
import {
  GoogleAuthProvider,
  signInWithPopup,
  signInWithEmailAndPassword,
  sendPasswordResetEmail,
  createUserWithEmailAndPassword,
  signOut,
} from "firebase/auth";

async function updateUser(newVal) {
  const userRef = doc(db, "utilisateurs", auth.currentUser.uid);
  await updateDoc(userRef, newVal);
}
async function getUser() {
  const userRef = doc(db, "utilisateurs", auth.currentUser.uid);
  const userSnap = await getDoc(userRef);
  return userSnap.data();
}

async function login(details) {
  const { email, password } = details;
  try {
    await signInWithEmailAndPassword(auth, email, password);
  } catch (error) {
    switch (error.code) {
      case "auth/user-not-found":
        alert("User not found");
        break;
      case "auth/wrong-password":
        alert("Wrong password");
        break;
      default:
        alert("Something went wrong : " + error.code);
        break;
    }
    return;
  }
}
async function resetPassword() {
  try {
    await sendPasswordResetEmail(auth, auth.currentUser.email);
    alert("Email send");
  } catch (error) {
    console.log(error.code);
    return;
  }
}
async function createUserEmailPassword(details) {
  try {
    await createUserWithEmailAndPassword(auth, details.email, details.password);
  } catch (error) {
    switch (error.code) {
      case "auth/email-already-in-use":
        alert("Email already in use");
        break;
      case "auth/invalid-email":
        alert("Invalid email");
        break;
      case "auth/operation-not-allowed":
        alert("Operation not allowed");
        break;
      case "auth/weak-password":
        alert("Weak password");
        break;
      default:
        alert("Something went wrong : " + error.code);
        break;
    }
    return;
  }
  await createUser(details);
}
async function createUser(details) {
  const { name, surname, size } = details;
  await setDoc(doc(db, "utilisateurs", auth.currentUser.uid), {
    name: name,
    surname: surname,
    size: size,
    cart: [],
    favorites: [],
    currency: {
      symbol: "$",
      code: "USD",
    },
    addresses: {
      firstname: "",
      lastname: "",
      billingAddress: {
        street: "",
        city: "",
        state: "",
        zip: "",
      },
      shippingAddress: {
        street: "",
        city: "",
        state: "",
        zip: "",
      },
      useSameAddress: false,
    },
  });
}

async function googleAuth() {
  const provider = new GoogleAuthProvider();
  try {
    const result = await signInWithPopup(auth, provider)
    return result
  } catch (error) {
    alert(error.message);
  }
}
async function signOutFunction() {
  await signOut(auth);
}

async function checkDiscountCode(code) {
  const discountRef = doc(db, "discountCode", code);
  const discountSnap = await getDoc(discountRef);
  return discountSnap.data();
}

export {
  updateUser,
  login,
  signOutFunction,
  getUser,
  resetPassword,
  createUser,
  createUserEmailPassword,
  googleAuth,
  checkDiscountCode,
};
