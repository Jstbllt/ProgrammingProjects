<template>

<div>
  <base-button :style="{color:color}" @click="updateFav"><font-awesome-icon icon="fa-solid fa-heart"/></base-button>
  <base-button @click="displayShoe()">
    <img :src="shoe.src" alt="shoe image" />
    <h3>{{ shoe.title }}</h3>
    <p class="price">
      {{ price }} {{ shoePrice.currency.symbol }}
    </p>
  </base-button>
 </div>
</template>

<script>
import BaseButton from "./BaseButton.vue";
import { getCurrencyExchange } from "@/services";

export default {
  components: { BaseButton },
  name: "ShoeBox",
  props: {
    shoe: {
      type: Object,
      required: true,
    },
  },

  data(){
    return {
      price:this.shoe.price
    }
  },
  computed:{
    color(){
      let color = 'black'
      const favorites = this.$store.getters.getUser?.favorites
      if (favorites!==undefined) {
        favorites.forEach(element => {
          if(element.id===this.shoe.id){
            color='red'
          }
        });
      }
      return color
    },
    shoePrice(){
      let details = {
        currency: {
          code: "USD",
          symbol: "$",
        },
      }
      const currency = this.$store.getters.getUser?.currency
      if (currency!==undefined) {
        details.currency.code = currency.code
        details.currency.symbol = currency.symbol
        if (currency.code === "JPY") {
          this.getPriceInJPY()
        } else if (currency.code === "EUR") {
          this.getPriceInEUR()
        } else if (currency.code === "GBP") {
          this.getPriceInGBP()
        }
      }
      return details
    },
  },
  methods: {
    displayShoe() {
      this.$router.push({ name: "ShoePage", params: { id: this.shoe.id } });
    },
    updateFav() {
      this.$store.dispatch("updateFavorites", this.shoe.id);
    },
    getPriceInJPY() {
      getCurrencyExchange("JPY", this.shoe.price).then((result) => {
        this.price =  Math.round(
          result.data.data.JPY.value * this.shoe.price
        );
      });
    },
    getPriceInEUR() {
      getCurrencyExchange("EUR", this.shoe.price).then((result) => {
        this.price =  Math.round(
          result.data.data.EUR.value * this.shoe.price
        );
      });
    },
    getPriceInGBP() {
      getCurrencyExchange("GBP", this.shoe.price).then((result) => {
        this.price =  Math.round(
          result.data.data.GBP.value * this.shoe.price
        );
      });
    },

  }
}

</script>

<style scoped>
.box img {
  width: 100%;
}

.box img {
  width: 100%;
}

.price {
  font-size: 25px;
  font-weight: bold;
  margin: 0;
}

.fav-button {
  text-align: right;
}
</style>
