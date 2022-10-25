<template>
  <main>
    <h1>Bag</h1>

    <div class="cart">
      <div v-if="cart.length === 0" class="cart-product">
        <p>There are no items in your bag.</p>
      </div>

      <div v-else class="cart-product">
        <div class="cart-header border_bottom">
          <div class="cart-header-details">
            <h4>Products details</h4>
          </div>
          <div class="cart-header-qty-price">
            <h4>Quantity</h4>
            <h4>Price</h4>
          </div>
        </div>

        <div class="products">
          <div v-for="product in cart" :key="product.shoe.id" class="product">
            <div class="product-details">
              <img :src="product.shoe.src" class="product-picture" />
              <div>
                <h5>{{ product.shoe.title }}</h5>
                <h5>Color: {{ product.color }}</h5>
                <h5>Size: {{ product.size }}</h5>
              </div>
            </div>

            <div class="product-qty-price">
              <div class="product-qty">
                <base-button
                  direction="Cart"
                  @click="$store.dispatch('deleteFromCart', product)"
                >
                  <font-awesome-icon
                    icon="fa-solid fa-minus"
                  ></font-awesome-icon>
                </base-button>

                <h4>{{ product.quantity }}</h4>

                <base-button
                  direction="Cart"
                  @click="$store.dispatch('addToCart', product)"
                >
                  <font-awesome-icon icon="fa-solid fa-plus" />
                </base-button>
              </div>

              <div class="product-price">
                <h4>${{ product.shoe.price }}</h4>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="cart-payment">
        <div class="flex-center">
          <div class="summary-header">
            <h2>Summary</h2>
          </div>
        </div>

        <div class="subtotal-container">
          <div class="subtotal">
            <h4>Subtotal</h4>
            <h4>${{ subTotalPrice }}</h4>
          </div>

          <div class="subtotal">
            + Estimated Delivery & Handling
            <div v-if="!shippingFees">FREE</div>
            <div v-else>${{ shippingFees }}</div>
          </div>

          <div v-if="shippingFees" class="spend-more">
            Spend ${{ 200 - subTotalPrice }} more to get free shipping.
          </div>

          <div class="subtotal">
            + Estimated Tax
            <div>—</div>
          </div>

          <div class="subtotal-container">
            <div class="subtotal discount-title">
              <h5>Discount Code</h5>
              <h5 v-if="discountPercentage">-{{ discountPercentage }}%</h5>
            </div>

            <div class="discount-row">
              <input placeholder="Enter code" v-model="discountCode" />

              <base-button
                color="signup"
                class="apply-button"
                @click="applyDiscountCode"
                >Apply</base-button
              >
            </div>
          </div>

          <div v-if="displayDiscountCodeError" class="subtotal error">
            This discount code is not valid.
          </div>
        </div>
        <div class="subtotal-container">
          <div class="total">
            <h4>TOTAL</h4>
            <h4>
              ${{
                (subTotalPrice + shippingFees) * (1 - discountPercentage / 100)
              }}
            </h4>
          </div>
        </div>

        <payment-button></payment-button>
      </div>
    </div>
  </main>
</template>

<script>
import BaseButton from "@/components/BaseButton.vue";
import { checkDiscountCode } from "@/firebase";
import PaymentButton from "@/components/PaymentButton";

// import ShoeBox from "@/components/ShoeBox.vue";

export default {
  name: "CartPage",
  components: { PaymentButton, BaseButton },
  data() {
    return {
      discountPercentage: 0,
      discountCode: "",
      displayDiscountCodeError: false,
    };
  },
  computed: {
    cart() {
      return this.$store.getters.getUser?.cart;
    },
    subTotalPrice() {
      var subTotalPrice = 0;
      if (this.cart) {
        this.cart.forEach((product) => {
          subTotalPrice += product.shoe.price * product.quantity;
        });
      }
      return subTotalPrice;
    },
    shippingFees() {
      var shippingFees = 0;
      if (this.cart) {
        if (this.subTotalPrice < 200) {
          shippingFees = 30;
        }
      }
      return shippingFees;
    },
  },
  methods: {
    async applyDiscountCode() {
      const result = await checkDiscountCode(this.discountCode.toUpperCase());
      if (result) {
        this.discountPercentage = result.discountPercentage;
        this.displayDiscountCodeError = false;
        return;
      }
      this.displayDiscountCodeError = true;
    },
  },
};
</script>

<style scoped>
main {
  display: flex;
  flex-direction: column;
  margin-bottom: 5%;
  padding: 2% 4%;
}
.cart {
  display: grid;
  grid-template-columns: 65% 30%;
  grid-gap: 5%;
}
.cart-product {
  grid-column: 1 / 2;
  grid-row: 1;
  display: flex;
  flex-direction: column;
  background-color: rgb(240, 240, 242);
  padding: 2%;
  border-radius: 2%;
}
.cart-header {
  display: grid;
  grid-template-columns: 65% 30%;
  grid-gap: 5%;
  border-bottom: solid lightgray 1px;
}
.cart-header-details {
  text-align: center;
  grid-column: 1 / 2;
  grid-row: 1;
}
.cart-header-qty-price {
  display: flex;
  justify-content: space-around;
  grid-column: 2 / 2;
  grid-row: 1;
}
.products {
  display: flex;
  flex-direction: column;
}
.product {
  display: grid;
  grid-template-columns: 65% 30%;
  grid-gap: 5%;
}
.product-details {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}
.product-picture {
  width: 100%;
  max-width: 150px;
}
.product-qty-price {
  display: flex;
  justify-content: space-around;
}
.product-qty {
  display: flex;
  align-items: center;
}
.product-price {
  display: flex;
  align-items: center;
}
.cart-payment {
  grid-column: 2 / 2;
  grid-row: 1;
  display: flex;
  flex-direction: column;
  background-color: rgb(240, 240, 242);
  border-radius: 2%;
  padding: 1%;
}
.subtotal-container {
  display: flex;
  flex-direction: column;
}
.subtotal {
  display: flex;
  justify-content: space-between;
  padding-left: 7%;
  padding-right: 7%;
}
.total {
  display: flex;
  justify-content: space-between;
  padding-left: 7%;
  padding-right: 7%;
  border-top: solid lightgray 1px;
  border-bottom: solid lightgray 1px;
  margin-top: 7%;
  margin-bottom: 7%;
}
.spend-more {
  color: rgb(100, 117, 140);
  font-size: 0.8rem;
  font-style: italic;
  margin-top: 0.5rem;
  margin-bottom: 1rem;
}

.summary-header {
  padding-left: 3%;
  width: 90%;
  border-bottom: solid lightgray 1px;
}

.flex-center {
  display: flex;
  justify-content: center;
}

.discount-row {
  display: flex;
  justify-content: start;
  align-items: center;
  padding-left: 7%;
  margin: 0;
}

.discount-title h5 {
  margin-bottom: 1%;
}

.error {
  color: #ef4444;
  font-size: 0.75rem;
  line-height: 1rem;
  font-style: italic;
  margin-top: 0.5rem;
  margin-bottom: 1rem;
}

.apply-button {
  font-size: 12px;
}

.svg-inline--fa {
  font-size: 1.5rem;
  color: rgb(100, 117, 140);
  margin-left: 0.5rem;
  margin-right: 0.5rem;
}

@media screen and (max-width: 1000px) {
  .cart,
  .product {
    display: flex;
    flex-direction: column;
  }

  .cart-header {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
  }

  .cart-header h4 {
    margin-top: 2%;
    margin-bottom: 2%;
  }

  .cart-header-qty-price {
    display: flex;
    justify-content: space-around;
  }

  .cart-product {
    margin-bottom: 5%;
  }

  .product-picture {
    width: 100%;
    max-width: 100px;
  }
}
</style>
