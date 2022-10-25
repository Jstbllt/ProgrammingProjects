<template>
  <div class="container">
    <div>
      <base-button
        color="creditCard"
        class="payment-button"
        @click="validateOrder"
        ><img src="../assets/carte-de-credit.png" class="credit-card" /> Credit
        card</base-button
      >
    </div>
    <div>
      <base-button color="paypal" class="payment-button" @click="validateOrder"
        ><img src="../assets/paypal-logo.png" class="paypal-logo"
      /></base-button>
    </div>
  </div>
</template>

<script>
import BaseButton from "@/components/BaseButton";
export default {
  name: "PaymentButton",
  components: { BaseButton },
  methods: {
    validateOrder() {
      if (confirm("Do you want to make the order ?") == true) {
        this.$store.dispatch("makeOrder");
        alert("Order passed ! Thanks you");
      }
    },
  },
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  width: 100%;
}

.payment-button {
  display: flex;
  align-items: center;
  justify-content: center;
}

.credit-card {
  width: 2vw;
  min-width: 20px;
  height: auto;
  margin-right: 1%;
}

.paypal-logo {
  width: 5vw;
  min-width: 70px;
}
</style>
