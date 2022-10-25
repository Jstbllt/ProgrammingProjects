<template>
  <main>
    <menu-account />
    <div class="content">
      <div class="menu-title">
        <h2>Account Confidentiality</h2>
      </div>
      <div class="article">
        <div class="section">
          <h3>Privacy policy</h3>
        </div>
        <div class="article-details">
          <article>
            ShoeShop will treat your personal information as confidential. Your
            personal details will not be given to anyone else without your prior
            consent or sold to anyone. ShoeShop will keep your email
            electronically for reference and audit purposes. Your email may be
            used for anonymous statistical reports to funders with the aim of
            improving the service provided to all clients by ShoeShop. Internet
            email is a way of making general enquiries but it is not a secure
            way to send personal information. ShoeShop makes every effort to
            ensure the security and integrity of emails on our systems.
            Unfortunately, no data transmission over the internet can be
            guaranteed to be 100% secure. As a result, while we strive to
            protect your personal information after we’ve received it, ShoeShop
            cannot ensure or guarantee the security of information when it is
            being transmitted. For more information, our privacy policy can be
            found HERE Emails from ShoeShop may be confidential and are intended
            solely for the use of the individual to whom they are addressed. If
            you are not the intended recipient of this email, you must neither
            take any action based upon its contents, nor copy or show it to
            anyone. Please contact the sender if you believe you have received
            this email in error.
          </article>
        </div>
      </div>
      <div class="article">
        <div class="section">
          <h3>Copyright</h3>
        </div>
        <div class="article-details">
          <article>
            Material on this website, including text and images, is protected by
            copyright. It may not be copied, reproduced, republished,
            downloaded, posted, broadcast or transmitted in any way except for
            your own personal, non-commercial use. Prior written consent of the
            copyright holder must be obtained for any other use of material.
            Copyright in all materials and/or works comprising or contained
            within this website remains with ShoeShop and other copyright
            owner(s) as specified. No part of this site may be distributed or
            copied for any commercial purpose.
          </article>
        </div>
      </div>
      <div class="article">
        <div class="section">
          <h3>Disclaimer</h3>
        </div>
        <div class="article-details">
          <article>
            ShoeShop has tried to ensure that the information on this website is
            accurate. However, ShoeShop will not accept liability for any loss,
            damage or inconvenience arising as a consequence of any use of or
            the inability to use any information on this website. ShoeShop
            endeavours to provide a service of the highest quality. However, we
            cannot guarantee that our service will be uninterrupted or
            error-free. We are not responsible for claims brought by third
            parties arising from your use of this website ShoeShop assumes no
            responsibility for the contents of linked websites. The inclusion of
            any link should not be taken as endorsement of any kind by ShoeShop,
            of the linked website or any association with its operators.
          </article>
        </div>
      </div>
    </div>
  </main>
</template>
<script>
import MenuAccount from "@/components/MenuAccount.vue";
export default {
  name: "ConfidentialityPage",
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
.privacy,
.article {
  width: 80%;
  margin-top: 2rem;
}
.section {
  border-bottom: solid #cfcfcf;
  border-width: 0px 0px 1px;
  display: flex;
  align-items: center;
}
.article-details {
  display: flex;
  flex-direction: column;
  width: 100%;
  text-align: justify;
  margin-top: 1.33rem;
}
</style>
