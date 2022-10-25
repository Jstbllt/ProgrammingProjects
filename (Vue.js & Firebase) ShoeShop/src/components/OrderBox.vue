<template>
  <div class="container">
    <div class="order">
      <div class="order-date">
        <h4>Date : {{ dateConverted }}</h4>
        <h4>Total Price : {{ order.price }} $</h4>
      </div>
      <div class="order-content">
        <div v-for="shoe in order.content" :key="shoe.title">
          <shoe-box class="box" :shoe="shoe.shoe" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ShoeBox from "./ShoeBox.vue";
export default {
  name: "OrderBox",
  components: { ShoeBox },
  props: {
    order: {
      type: Object,
      default: null,
    },
  },
  data() {
    return {
      dateConverted: null,
    };
  },
  methods: {
    timeConverter(timestamp) {
      var a = new Date(timestamp * 1000);
      var months = [
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "Jun",
        "Jul",
        "Aug",
        "Sep",
        "Oct",
        "Nov",
        "Dec",
      ];
      var year = a.getFullYear();
      var month = months[a.getMonth()];
      var date = a.getDate();
      var hour = a.getHours();
      var min = a.getMinutes();
      var sec = a.getSeconds();
      var time =
        date + " " + month + " " + year + " " + hour + ":" + min + ":" + sec;
      return time;
    },
  },
  mounted() {
    this.dateConverted = this.timeConverter(this.order.date.seconds);
  },
};
</script>
<style scoped>
.container {
  display: flex;
  flex-direction: column;
}
.order-content {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-evenly;
}
.order {
  display: flex;
  flex-direction: column;
  background-color: rgb(240, 240, 242);
  border: solid black 1px;
  width: 100%;
}
.box {
  margin-bottom: 10%;
  width: 150px;
  margin: 15px;
  padding: 15px;
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
}

.order-date {
  display: flex;
  justify-content: center;
  gap: 5%;
}
</style>
