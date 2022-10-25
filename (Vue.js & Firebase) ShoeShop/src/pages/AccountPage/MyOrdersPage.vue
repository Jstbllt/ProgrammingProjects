<template>
  <main>
    <menu-account />
    <div class="content">
      <div class="menu-title">
        <h2>My orders</h2>
      </div>
      <div class="orders-container">
        <div class="order" v-for="order in orders" :key="order.date">
          <order-box :order="order" />
        </div>
      </div>
      <div v-if="orders===undefined">
          <p>You don't have any order yet !</p>
      </div>
    </div>
  </main>
</template>
<script>
import MenuAccount from "@/components/MenuAccount.vue";
import OrderBox from "@/components/OrderBox.vue";
export default {
  name: "MyOrdersPage",
  components: { MenuAccount, OrderBox },
  computed: {
    orders() {
      return this.$store.getters.getUser.orders;
    },
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
  margin-bottom: 2rem;
  text-align: center;
}
.menu-title {
  text-align: center;
  margin-top: 1rem;
}
.orders-container {
  margin-top: 1rem;
  width: 90%;
}
.order {
  margin-top: 1rem;
}
</style>
