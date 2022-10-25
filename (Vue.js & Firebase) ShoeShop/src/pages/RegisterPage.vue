<template>
	<main class="login">
		<section class="forms">
			<div class="buttons margin-bottom">
				<div class="left-button"><base-button direction="Login">Login</base-button></div>	
				<div class="right-button"><base-button direction="Register">Sign up</base-button></div>
			</div>
			<base-button @click="signInWithGoogle" color="login"><font-awesome-icon icon="fa-brands fa-google"/><span></span> Sign up with google</base-button>
			<div class="or">
				<span class="vector"></span>
				<p>or</p>
				<span class="vector"></span>
			</div>
			<form class="register" @submit.prevent="register">
				<input
					class="margin-bottom"
					type="text" 
					placeholder="Name"
					v-model="register_form.name" />
				<input
					class="margin-bottom"
					type="text" 
					placeholder="Surname"
					v-model="register_form.surname" />
				<input
					class="margin-bottom"
					type="number" 
					placeholder="Shoe size"
					v-model="register_form.size" />
				<input
					class="margin-bottom"
					type="email" 
					placeholder="Email address"
					v-model="register_form.email" />
				<input 
					class="margin-bottom"
					type="password" 
					placeholder="Password" 
					v-model="register_form.password" />
				<input
					class="signup-button"
					type="submit" 
					value="Sign up" />
			</form>

			
		</section>
	</main>
</template>

<script>
import { ref } from 'vue'
import { useStore } from 'vuex'
import BaseButton from '@/components/BaseButton.vue';
export default {
  components: { BaseButton },
    name:"LoginPage",
	setup () {
		const register_form = ref({});
		const store = useStore();
		const register = () => {
			store.dispatch('register', register_form.value);
		}
		const signInWithGoogle = () => {
			store.dispatch('authWithGoogle');
		}
		return {
			register_form,
			register,
			signInWithGoogle
		}
	}
}
</script>

<style scoped>
	.login{
		display: flex;
		flex-direction: column;
		align-content: center;
		align-items: center;
	}
	.forms{
		display: flex;
		flex-direction: column;
		align-content: center;
		border: solid 1px rgb(62,62,64);
		border-radius: 2px;
		padding: 20px;
		width:300px;
		margin: 7%;
	}
	@media screen and (max-width: 500px){
		.forms{
		width:70%;
		}
	}
	.register{
		display: flex;
		flex-direction: column;
		align-content: center;
	}
	.or{
		display: flex;
		flex-direction: row;
		align-items: center;
		color: rgb(189,189,191);
	}
	.vector{
		border: solid rgb(189,189,191) 0.5px;
		height: 0px;
		width: 100%;
		margin: 5px;
	}
	h1{
		font-size: 90%;
	}
	.buttons{
		display: flex;
		justify-content:space-between;
		padding: 0px 5% 0px 5%;
	}
	.margin-bottom{
		margin-bottom: 5%;
	}
	input{
		padding: 5%;
	}
	.signup-button{
		background-color: rgb(100,117,140);
		color: white;
		font-weight: bold;
		border:none;
	}
	.signup-button:hover{
		background-color: black;
		transition: 0.5s;
	}
	.left-button{
		padding: 2%;
		text-align: center;
		border-bottom: solid gray;
		width: 45%;
	}
	.right-button{
		padding: 2%;
		text-align: center;
		border-bottom: solid black;
		width: 45%;
	}
	p{
		margin: 2%;
	}
	.log{
		display: flex;
		flex-direction: column;
		align-content: center;
	}
	#login:active{
		background-color: aqua;
	}
</style>