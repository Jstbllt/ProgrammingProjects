<template>
  <main>
    <menu-account />
    <div class="content">
      <div class="menu-title">
        <h2>Account Securities</h2>
      </div>
      <div class="authentification">
        <div class="section">
          <h3>Two-factor authentification</h3>
        </div>
        <div class="authentification-details">
          <article>
            Two-step verification enhances the overall security of your ShoeShop
            account. After activation, in addition to your password, you must
            enter a code sent to a secure mobile device to log in.
          </article>
          <div class="button-div">
            <base-button color="signup" class="enable-button" :disabled="true"
              >In Development</base-button
            >
          </div>
        </div>
      </div>
      <div class="faq">
        <div class="section">
          <h3>FAQ</h3>
        </div>
        <div class="faq-details">
          <div class="questions">
            <article>
              <h4>Why enable two-step verification ?</h4>
              We all use passwords that can be too simple. As our pets would
              say, the error is human. Two-step verification ensures that your
              ShoeShop account is protected even if your password is
              compromised.
            </article>

            <article>
              <h4>What is required to set up the two-step verification ?</h4>
              You must have a phone that can receive SMS messages to activate
              the two-step verification.
            </article>

            <article>
              <h4>How do I configure two-step verification ?</h4>
              You must have a phone that can receive SMS messages to activate
              the two-step verification.You must have a phone that can receive
              SMS messages to activate the two-step verification.You must have a
              phone
            </article>
          </div>
          <div class="faq-picture">
            <img src="@/assets/2fa.png" class="iphone-picture" />
          </div>
        </div>
      </div>
    </div>
  </main>
</template>
<script>
import MenuAccount from "@/components/MenuAccount.vue";
import BaseButton from "@/components/BaseButton.vue";
export default {
  name: "SecuritiesPage",
  components: { MenuAccount, BaseButton },
};
</script>
<style scoped>
main {
  display: flex;
  margin-bottom:30px ;
}
.content {
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  width: 100%;
  align-content: center;
}
.menu-title {
  text-align: center;
  margin-top: 1rem;
}
.authentification,
.faq {
  width: 80%;
  margin-top: 2rem;
}
.section {
  border-bottom: solid #cfcfcf;
  border-width: 0px 0px 1px;
  display: flex;
  align-items: center;
}
.faq-details {
  display: flex;
  flex-direction: row;
  width: 100%;
  text-align: justify;
}

.authentification-details {
  display: flex;
  flex-direction: column;
  width: 100%;
  text-align: justify;
  margin-top: 1.33rem;
}
.iphone-picture {
  width: 100%;
}
.enable-button:hover {
  cursor: not-allowed;
}

.button-div {
  width: 10rem;
  margin-top: 1rem;
}
.button-div a{
  padding: 6px;
}

@media screen and (max-width: 1300px) {
  .faq-details {
    flex-direction: column;
  }
}
</style>
