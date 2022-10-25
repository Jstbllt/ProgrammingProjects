<template>
  <div>
    <label v-if="title" class="label">{{ title }}</label>
    <div class="form">
      <div class="street">
        <label class="label" :for="idPrefix + '_street'">Street</label>
        <Field
          class="input"
          type="text"
          :name="idPrefix + '_street'"
          :autocomplete="idPrefix + ' street-address'"
          placeholder="Street"
          @input="updateField"
          v-model="street"
          :rules="validateStreet"
        />
        <error-message :name="idPrefix + '_street'" class="error" />
      </div>

      <div class="address-details-container">
        <div class="address-details">
          <label class="label" :for="idPrefix + '_city'">City</label>
          <Field
            :for="idPrefix + 'city'"
            class="input"
            type="text"
            :id="idPrefix + 'city'"
            :name="idPrefix + '_city'"
            :autocomplete="idPrefix + ' address-level2'"
            placeholder="City"
            @input="updateField"
            v-model="city"
            :rules="validateCity"
          />
          <error-message :name="idPrefix + '_city'" class="error" />
        </div>

        <div class="address-details">
          <label class="label" :for="idPrefix + '_state'">State</label>
          <Field
            :for="idPrefix + '_state'"
            class="input"
            type="text"
            :id="idPrefix + '_state'"
            :name="idPrefix + '_state'"
            :autocomplete="idPrefix + ' address-level1'"
            placeholder="State"
            @input="updateField"
            v-model="state"
            :rules="validateState"
          />
          <error-message :name="idPrefix + '_state'" class="error" />
        </div>

        <div class="address-details">
          <label class="label" :for="idPrefix + '_zip'">Zip</label>
          <Field
            :for="idPrefix + '_zip'"
            class="input"
            type="text"
            :id="idPrefix + '_zip'"
            :name="idPrefix + '_zip'"
            :autocomplete="idPrefix + ' postal-code'"
            placeholder="Zip"
            @input="updateField"
            v-model="zip"
            :rules="validateZip"
          />
          <error-message :name="idPrefix + '_zip'" class="error" />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { Field, ErrorMessage } from "vee-validate";

export default {
  name: "AddressSubForm",
  props: {
    title: {
      type: String,
      default: "",
    },
    idPrefix: {
      type: String,
      default: "",
    },
    addresse: {
      type: Object,
      default: null,
    },
  },
  components: {
    Field,
    ErrorMessage,
  },
  data() {
    return {
      street: null,
      city: null,
      state: null,
      zip: null,
    };
  },
  methods: {
    validateStreet(value) {
      if (!value) return "Street name field is required";
      return true;
    },
    validateCity(value) {
      if (!value) return "City name field is required";
      const regex = /^([a-zA-Z]+\s)*[a-zA-Z]+$/i; //only char and space
      if (!regex.test(value)) {
        return "City field must contains only alphabetic characters";
      }
      return true;
    },
    validateState(value) {
      if (!value) return "State name field is required";
      const regex = /^([a-zA-Z]+\s)*[a-zA-Z]+$/i; //only char and space
      if (!regex.test(value)) {
        return "State field must contains only alphabetic characters";
      }
      return true;
    },
    validateZip(value) {
      if (!value) return "City zip code field is required";
      const regex = /^^[0-9]*$/i; //only number
      if (!regex.test(value)) {
        return "Zip field must contains only numbers";
      }
      return true;
    },
    updateField() {
      this.$emit("address-updated", {
        street: this.street,
        city: this.city,
        state: this.state,
        zip: this.zip,
      });
    },
  },
  mounted() {
    if (this.addresse) {
      this.city = this.addresse.city;
      this.state = this.addresse.state;
      this.zip = this.addresse.zip;
      this.street = this.addresse.street;
    }
  },
};
</script>
<style scoped>
.label {
  display: block;
  margin-bottom: 0.5rem;
  color: #374151;
  font-size: 0.875rem;
  line-height: 1.25rem;
  font-weight: 700;
}
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
.street {
  margin-bottom: 1rem;
  display: flex;
  flex-direction: column;
}
.address-details-container {
  display: flex;
  margin-bottom: 1rem;
}
.address-details {
  display: flex;
  flex-direction: column;
  padding-right: 0.5rem;
  width: 33.333333%;
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
  .form {
    padding-left: 1rem;
    padding-right: 1rem;
  }
  .address-details-container {
    flex-direction: column;
  }
  .address-details {
    width: 100%;
    margin-bottom: 1rem;
  }
}
</style>
