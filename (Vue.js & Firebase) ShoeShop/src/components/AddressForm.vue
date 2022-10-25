<template>
  <div class="container">
    <Form @submit="onSubmit">
      <div class="form">
        <div class="name-container">
          <div class="name-field">
            <label for="firstName" class="label">First Name</label>
            <Field
              class="input"
              type="text"
              name="firstname"
              placeholder="First Name"
              v-model="firstName"
              :rules="validateName"
            />
            <error-message name="firstname" class="error" />
          </div>

          <div class="name-field">
            <label class="label">Last Name</label>
            <Field
              class="input"
              type="text"
              name="lastname"
              placeholder="Last Name"
              v-model="lastName"
              :rules="validateName"
            />
            <error-message name="lastname" class="error" />
          </div>
        </div>

        <address-sub-form
          title="Billing Address"
          @address-updated="setBillingAddress"
          idPrefix="billing"
          :addresse="addresses.billingAddress"
        />

        <div>
          <input
            :value="true"
            class="checkbox"
            type="checkbox"
            v-model="useSameAddress"
            @click="setSameAddress"
          />
          <label class="label"> Use same address</label>
        </div>
        <address-sub-form
          v-if="!useSameAddress"
          title="Shipping Address"
          @address-updated="setShippingAddress"
          idPrefix="shipping"
          :addresse="addresses.shippingAddress"
        />

        <div class="submit-button">
          <input type="submit" value="Update" class="update-button" />

          <!-- <base-button  value="submit" color="signup"
            >Update
          </base-button> -->
        </div>
      </div>
    </Form>
  </div>
</template>
<script>
import AddressSubForm from "./AddressSubForm.vue";
import { Form, Field, ErrorMessage } from "vee-validate";

export default {
  name: "AddressForm",
  components: { AddressSubForm, Form, Field, ErrorMessage },
  emits: ["updateAddress"],
  props: {
    addresses: {
      type: Object,
      default: null,
    },
  },
  data() {
    return {
      firstName: null,
      lastName: null,
      billingAddress: null,
      shippingAddress: null,
      useSameAddress: false,
    };
  },
  methods: {
    setBillingAddress(payload) {
      this.billingAddress = payload;
    },
    setShippingAddress(payload) {
      this.shippingAddress = payload;
    },
    setSameAddress() {
      this.shippingAddress = this.billingAddress;
    },
    validateName(value) {
      if (!value) return "This field is required";
      const regex = /^[A-Za-zÀ-ÖØ-öø-ÿ"  *"]+$/i; //only char with space and accents
      if (!regex.test(value)) {
        return "This field must be a valid name";
      }
      return true;
    },
    onSubmit() {
      this.$emit("updateAddress", this.$data);
    },
  },
  mounted() {
    if (this.addresses) {
      this.firstName = this.addresses.firstName;
      this.lastName = this.addresses.lastName;
      this.useSameAddress = this.addresses.useSameAddress;
      this.billingAddress = this.addresses.billingAddress;
      this.shippingAddress = this.addresses.shippingAddress;
    }
  },
};
</script>

<style scoped>
.form {
  padding-left: 2rem;
  padding-right: 2rem;
  padding-top: 1.5rem;
  padding-bottom: 2rem;
  margin-top: 1rem;
  margin-bottom: 1rem;
  background-color: #ffffff;
  border-radius: 0.25rem;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1),
    0 2px 4px -1px rgba(0, 0, 0, 0.06);
}
.name-container {
  display: flex;
  margin-bottom: 1rem;
}
.name-field {
  width: 50%;
  display: flex;
  flex-direction: column;
}
.label {
  display: inline-block;
  margin-bottom: 0.5rem;
  color: #374151;
  font-size: 0.875rem;
  line-height: 1.25rem;
  font-weight: 700;
}
.input {
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  padding-left: 0.75rem;
  padding-right: 0.75rem;
  color: #374151;
  outline-color: #a0ccf0;
  line-height: 1.25;
  width: 90%;
  border-radius: 0.25rem;
  border: 1px;
  appearance: none;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
}
.submit-button {
  margin-top: 3rem;
  width: 40%;
}

/* bg: "rgb(100,117,140)",
    fontColor: "rgb(255,255,255)",
    hoverBg: "rgb(13,13,13)",
    hoverFontColor: "rgb(255,255,255)",
    border: "0", */
.update-button {
  background: rgb(100, 117, 140);
  color: rgb(255, 255, 255);
  box-shadow: 0;
  font-size: 100%;
  text-align: justify;
  margin: 1%;
  padding: 2%;
  cursor: pointer;
  font-weight: 700;
  transition: all 0.4s ease-in-out;
  text-decoration: none;
}
.update-button:hover {
  transition-delay: 1ms;
  background: rgb(13, 13, 13);
  color: rgb(255, 255, 255);
}
.checkbox {
  accent-color: green;
}
.error {
  color: #ef4444;
  font-size: 0.75rem;
  line-height: 1rem;
  font-style: italic;
  margin-top: 0.5rem;
  margin-bottom: 1rem;
}
@media screen and (max-width: 675px) {
  .name-container {
    flex-direction: column;
  }
  .name-field {
    display: flex;
    flex-direction: column;
    width: 100%;
    margin-bottom: 1rem;
  }
}
</style>
