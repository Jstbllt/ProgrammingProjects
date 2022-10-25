<template>
  <main>
    <menu-account />
    <div class="content">
      <div class="menu-title"></div>
      <h2>Loyalty Program</h2>
    </div>
  </main>
</template>
<script>
import MenuAccount from "@/components/MenuAccount.vue";
export default {
  name: "LoyaltyProgramPage",
  components: { MenuAccount },
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
}
.menu-title {
  text-align: center;
  margin-top: 1rem;
}
</style>
