import { createStore } from "vuex";
import router from "../router";
import {
  auth,
  updateUser,
  getUser,
  login,
  createUser,
  googleAuth,
  createUserEmailPassword,
  signOutFunction,
} from "../firebase";
import { arrayUnion } from "firebase/firestore";

const store = createStore({
  state: {
    user: null,
    currentShoe: {},
    shoes: [
      {
        id: 0,
        brand: "New Balance",
        title: "New Balance 574 Classic",
        description:
          "The 574 was designed as a reliable, versatile shoe, rather than a showcase of revolutionary technology and high-end materials. This unassuming, unassuming versatility is exactly what propelled the 574 into the top shoes of all time.",
        src: "/models/chaussure1.png",
        price: 188,
        grade: 1,
        obj3DSettings: {
          link: "/models/chaussure1/scene.gltf",
          cameraPosition: [-10, 5, 8],
          instensity: 3,
        },
      },
      {
        id: 1,
        brand: "Nike",
        title: "Nike Air Zoom Pegasus",
        description:
          "The winged shoe is back to help you brave the rain. The water-repellent finish helps keep you dry, and the comfortable Fleece-like interior keeps your feet warm during your runs in bad weather. The rugged grip and dual Zoom Air units provide optimal traction and soft cushioning so you can give it your all in the elements.",
        src: "/models/chaussure2.png",
        price: 212,
        grade: 1,
        obj3DSettings: {
          link: "/models/chaussure2/scene.gltf",
          cameraPosition: [3, 1, -2],
        },
      },
      {
        id: 2,
        brand: "RTFKT",
        title: "Rtfkt Creator One",
        description:
          "The RTFKT Creator One sneaker is a virtual high top sneaker based on a SWEAR Air Revive sole. Made by RTFKT studios for creators to skin, mod and remix, merging realities to be made IRL for the winners of the challenge.",
        src: "/models/chaussure3.png",
        price: 128,
        grade: 1,
        obj3DSettings: {
          link: "/models/chaussure3/scene.gltf",
          cameraPosition: [50, 10, 30],
        },
      },
      {
        id: 3,
        brand: "Adidas",
        title: "Adidas Swift Run 22",
        description:
          "Inspired by running but designed for everyday life, this sneaker is comfortable with its mesh upper and EVA midsole for optimal cushioning. And it looks great. From morning to night.",
        src: "/models/chaussure4.png",
        price: 331,
        grade: 1,
        obj3DSettings: {
          link: "/models/chaussure4/scene.gltf",
          cameraPosition: [220, 200, 200],
        },
      },
      {
        id: 4,
        brand: "Adidas",
        title: "Adidas Ozelia",
        description:
          "We may be a long way from the '90s, but this Ozelia shoe is rooted in that remarkable, experimental era. Inspired by the adidas archives, this bold sneaker has a chunky design that won't go unnoticed. It has a dynamic look and the Adiprene cushioning gives you maximum comfort when you pick up the pace throughout the day.",
        src: "/models/chaussure5.png",
        price: 865,
        grade: 1,
        obj3DSettings: {
          link: "/models/chaussure5/scene.gltf",
          cameraPosition: [4, 2, 4],
        },
      },
      {
        id: 5,
        brand: "Nike",
        title: "Nike Air Jordan 1 High",
        description:
          "The iconic Air Jordan 1 High silhouette has a new version with the famous University blue color code.",
        src: "/models/chaussure6.png",
        price: 192,
        grade: 1,
        obj3DSettings: {
          link: "/models/chaussure6/scene.gltf",
          cameraPosition: [-7, 2, -6],
        },
      },
      {
        id: 6,
        brand: "Fila",
        title: "Pink Winter Boot",
        description: "Women’s Winter Snow Boot",
        src: "/models/chaussure7.png",
        price: 199,
        grade: 1,
        obj3DSettings: {
          link: "/models/chaussure7/scene.gltf",
          cameraPosition: [-0.4, 0.4, 0.4],
          instensity: 15,
        },
      },
      {
        id: 7,
        brand: "Nike",
        title: "Nike Air Jordan Mid",
        description:
          "Air Jordan is a line of basketball shoes and athletic clothing produced by American corporation Nike.",
        src: "/models/chaussure8.png",
        price: 399,
        grade: 1,
        obj3DSettings: {
          link: "/models/chaussure8/scene.gltf",
          cameraPosition: [0.5, 0.5, 0],
          instensity: 3,
        },
      },
      {
        id: 8,
        brand: "Nike",
        title: "Nike Air Presto x Off-White Black",
        description:
          "Considered by many to be one of the best silhouettes from 'The Ten' collection, the Nike Air Presto x Off-White makes its return in 2018 in a brand new look. The Nike Air Presto Off-White Black adopts an all-black look with a mesh upper and an all-black tonal sole. This silhouette takes the aesthetics of the first model like the deconstructed swoosh or the branding-signature 'AIR' of Virgil Abloh.",
        src: "/models/chaussure9.PNG",
        price: 1300,
        grade: 1,
        obj3DSettings: {
          link: "/models/chaussure9/scene.gltf",
          cameraPosition: [-90, 20, 10],
          instensity: 30,
        },
      },
    ],
  },
  getters: {
    getShoes(state) {
      return state.shoes;
    },
    getUser(state) {
      return state.user;
    },
    getCart(state) {
      return state.user.cart;
    },
    getFavorites(state) {
      return state.user.favorites;
    },
    getCurrentShoe(state) {
      return state.currentShoe;
    },
    getBrands(state) {
      let allBrands = [];
      state.shoes.forEach((shoe) => {
        if (allBrands.includes(shoe.brand) === false) {
          allBrands.push(shoe.brand);
        }
      });
      return allBrands;
    },
  },
  mutations: {
    setUser(state, user) {
      state.user = user;
    },
    setCart(state, newCart) {
      state.user.cart = newCart;
    },
    setFavorites(state, newFavorites) {
      state.user.favorites = newFavorites;
    },
    setCurrentShoe(state, shoe) {
      state.currentShoe = shoe;
    },
    setGrade(state, grade) {
      state.currentShoe.grade = grade;
    },
    clearUser(state) {
      state.user = null;
    },
  },
  actions: {
    emptyCart({ commit }) {
      let newCart = [];
      commit("setCart", newCart);
    },
    async deleteElemFromCart({ commit, state }, shoeid) {
      let newCart = state.user.cart;
      newCart.forEach((elem) => {
        if (elem.shoe.id === parseInt(shoeid)) {
          newCart.splice(newCart.indexOf(elem), 1);
        }
      });
      await updateUser({ cart: newCart });
      commit("setCart", newCart);
    },
    async deleteFromCart({ commit, state }, article) {
      let newCart = state.user.cart;
      newCart.forEach((elem) => {
        if (
          elem.shoe.id === parseInt(article.shoe.id) &&
          elem.color === article.color &&
          elem.size === article.size
        ) {
          if (elem.quantity > 1) {
            elem.quantity = elem.quantity - 1;
          } else {
            newCart.splice(newCart.indexOf(elem), 1);
          }
        }
      });
      await updateUser({ cart: newCart });
      commit("setCart", newCart);
    },
    //add shoe to fav if is not here and remove it if its already here
    async updateFavorites({ commit, state }, shoeid) {
      let finded = false;
      let newFav = state.user.favorites;
      newFav.forEach((elem) => {
        if (elem.id === parseInt(shoeid)) {
          newFav.splice(newFav.indexOf(elem), 1);
          finded = true;
        }
      });
      if (!finded) {
        newFav.push(state.shoes[shoeid]);
      }

      await updateUser({ favorites: newFav });
      commit("setFavorites", newFav);
    },
    async addToCart({ commit, state }, article) {
      let finded = false;
      let newCart = state.user.cart;
      newCart.forEach((elem) => {
        if (
          elem.shoe.id === parseInt(article.shoe.id) &&
          elem.color === article.color &&
          elem.size === article.size
        ) {
          elem.quantity = elem.quantity + 1;
          finded = true;
        }
      });
      if (!finded) {
        newCart = [].concat(newCart, [
          {
            shoe: state.shoes[article.shoe.id],
            quantity: 1,
            color: article.color,
            size: article.size,
          },
        ]);
      }

      await updateUser({ cart: newCart });
      commit("setCart", newCart);
    },
    updateGrade({ commit }, newGrade) {
      commit("setGrade", newGrade);
    },
    setCurrentShoe({ commit, state }, shoeid) {
      let shoeFound = {};
      state.shoes.forEach((shoe) => {
        if (shoeid == shoe.id) {
          shoeFound = shoe;
        }
      });
      commit("setCurrentShoe", shoeFound);
    },
    async login({ commit }, details) {
      await login(details);
      const user = await getUser();
      commit("setUser", user);
      router.push("/");
    },

    async register({ commit }, details) {
      const { name, surname, size } = details;
      await createUserEmailPassword(details);

      commit("setUser", {
        name: name,
        surname: surname,
        size: size,
        cart: [],
        favorites: [],
        currency: {
          code: "USD",
          symbol: "$",
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
      router.push("/");
    },
    async authWithGoogle({ commit }) {
      const userSnap = await googleAuth();
      const user = await getUser(userSnap.user.uid);
      if (user === undefined) {
        const details = {
          name: userSnap.user.displayName,
          surname: "undifine",
          size: "undifine",
          cart: [],
          favorites: [],
          currency: {
            code: "USD",
            symbol: "$",
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
        };
        await createUser(details);
        commit("setUser", details);
        router.push("/account/settings");
        alert("Please, register your informations");
      } else {
        router.push("/");
      }
    },
    async logout({ commit }) {
      await signOutFunction();
      commit("clearUser");
      router.push("/login");
    },
    fetchUser({ commit }) {
      auth.onAuthStateChanged(async (user) => {
        if (user === null) {
          commit("clearUser", user);
        } else {
          const user = await getUser();
          commit("setUser", user);
          if (router.isReady() && router.currentRoute.value.path == "/login") {
            router.push("/");
          }
        }
      });
    },
    async updateProfil({ commit }, details) {
      await updateUser({
        name: details.name,
        surname: details.surname,
        size: details.size,
        currency: details.currency,
      });
      const user = await getUser();
      commit("setUser", user);
    },
    async updateAddresses({ commit }, addresses) {
      await updateUser({
        addresses: addresses,
      });
      const user = await getUser();
      commit("setUser", user);
    },
    async makeOrder({ commit, state }) {
      var totalPrice = 0;
      state.user.cart.forEach((item) => {
        totalPrice += item.shoe.price;
      });
      const order = {
        content: state.user.cart,
        date: new Date(),
        price: totalPrice,
      };
      await updateUser({
        orders: arrayUnion(order),
      });
      const user = await getUser();
      commit("setUser", user);
      let newCart = [];
      commit("setCart", newCart);
    },
  },
});

export default store;
