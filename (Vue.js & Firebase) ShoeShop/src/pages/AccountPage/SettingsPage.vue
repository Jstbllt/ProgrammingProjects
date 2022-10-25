<template>
  <main>
    <menu-account />
    <div class="content">
      <div class="menu-title">
        <h2>Account settings</h2>
      </div>
      <div class="profil">
        <div class="section">
          <h3>Profil</h3>
          <div class="edit-button">
            <base-button
              v-if="!editProfilDetails"
              @click="editProfilDetails = true"
              color="login"
            >
              Edit
            </base-button>
            <base-button
              v-if="editProfilDetails"
              @click="handleCancelEditProfil"
              color="login"
            >
              Cancel
            </base-button>
            <base-button
              v-if="editProfilDetails"
              @click="updateProfil"
              color="signup"
              >Save</base-button
            >
          </div>
        </div>

        <div class="profil-details">
          <div class="details">
            <label>Firstname </label>
            <input
              v-model="userInfo.name"
              :readonly="editProfilDetails ? false : true"
              :disabled="editProfilDetails ? false : true"
              :class="editProfilDetails ? 'input-showned' : 'input-hidden'"
            />
          </div>
          <div class="details">
            <label>Lastname </label>
            <input
              v-model="userInfo.surname"
              :readonly="editProfilDetails ? false : true"
              :disabled="editProfilDetails ? false : true"
              :class="editProfilDetails ? 'input-showned' : 'input-hidden'"
            />
          </div>
          <div class="details">
            <label>Shoe size </label>
            <input
              v-model="userInfo.size"
              type="number"
              :readonly="editProfilDetails ? false : true"
              :disabled="editProfilDetails ? false : true"
              :class="editProfilDetails ? 'input-showned' : 'input-hidden'"
            />
          </div>
          <div class="details">
            <label>Currency </label>
            <select
              v-model="currencySelected"
              :readonly="editProfilDetails ? false : true"
              :disabled="editProfilDetails ? false : true"
              :class="editProfilDetails ? 'input-showned' : 'input-hidden'"
            >
              <option value="USD">$ USD</option>
              <option value="EUR">€ EUR</option>
              <option value="GBP">£ GBP</option>
              <option value="JPY">¥ JPY</option>
            </select>
          </div>
        </div>
      </div>
      <div class="addresses">
        <div class="section">
          <h3>Addresses</h3>
          <div class="edit-button">
            <base-button
              v-if="!editFormAddress"
              @click="editFormAddress = true"
              color="login"
            >
              Edit
            </base-button>
            <base-button
              v-if="editFormAddress"
              @click="handleCancelEditFormAdress"
              color="login"
            >
              Cancel
            </base-button>
          </div>
        </div>
      </div>
      <address-form
        v-if="editFormAddress"
        @updateAddress="updateAddress"
        :addresses="$store.state.user.addresses"
      />
      <div class="reset-password">
        <div class="section">
          <h3>Password</h3>
        </div>
        <div class="reset-password-details">
          <base-button @click="resetPassword" direction="Settings" color="login"
            >Reset password</base-button
          >
        </div>
      </div>
    </div>
  </main>
</template>
<script>
import MenuAccount from "@/components/MenuAccount.vue";
import AddressForm from "@/components/AddressForm.vue";
import BaseButton from "@/components/BaseButton.vue";
import { resetPassword } from "@/firebase";
export default {
  name: "SettingsPage",
  components: { MenuAccount, AddressForm, BaseButton },
  data() {
    return {
      editFormAddress: false,
      editProfilDetails: false,
      userInfo: {
        name: "",
        surname: "",
        size: 0,
        currency: null,
      },
      currencySelected: "",
    };
  },
  methods: {
    async resetPassword() {
      await resetPassword();
    },
    updateProfil() {
      this.editProfilDetails = false;
      if (this.currencySelected === "EUR") {
        this.userInfo.currency.symbol = "€";
      }
      if (this.currencySelected === "JPY") {
        this.userInfo.currency.symbol = "¥";
      }
      if (this.currencySelected === "GBP") {
        this.userInfo.currency.symbol = "£";
      }
      if (this.currencySelected === "USD") {
        this.userInfo.currency.symbol = "$";
      }
      this.userInfo.currency.code = this.currencySelected;
      this.$store.dispatch("updateProfil", this.userInfo);
    },
    updateAddress(addresses) {
      this.$store.dispatch("updateAddresses", addresses);
      this.editFormAddress = false;
    },
    resetInfo() {
      this.userInfo.name = this.$store.state.user.name;
      this.userInfo.surname = this.$store.state.user.surname;
      this.userInfo.size = this.$store.state.user.size;
      this.userInfo.currency = this.$store.state.user.currency;
      this.currencySelected = this.$store.state.user.currency.code;
    },
    handleCancelEditProfil() {
      this.editProfilDetails = false;
      this.resetInfo();
    },
    handleCancelEditFormAdress() {
      this.editFormAddress = false;
    },
  },
  created() {
    this.resetInfo();
  },
};
</script>
<style scoped>
main {
  display: flex;
}
.content {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  width: 100%;
  align-content: center;
  margin-bottom: 5%;
}
.menu-title {
  text-align: center;
  margin-top: 1rem;
}
.profil,
.reset-password {
  width: 80%;
  margin-top: 2rem;
}
h3 {
  width: 100%;
}

.section {
  border-bottom: solid #cfcfcf;
  border-width: 0px 0px 1px;
  margin-bottom: 2rem;
  display: flex;
  align-items: center;
}
.edit-button {
  display: flex;
  justify-content: flex-end;
  width: 40%;
}
.reset-password-details {
  display: flex;
  width: 40%;
  min-width: fit-content;
}
.profil-details {
  display: flex;
  flex-wrap: wrap;
  width: 90%;
  gap: 10%;
}

.details {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  margin-bottom: 2rem;
}

.input-hidden {
  border: 0;
  color: black;
  font-size: 16px;
}
input:disabled {
  background-color: white;
}
.input-showned {
  font-size: 16px;
  border: 1px;
  outline-color: #a0ccf0;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
}
</style>
