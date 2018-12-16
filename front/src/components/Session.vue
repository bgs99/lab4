<template>
  <div>
    <input type="button" value="Logout" @click="logout" v-if="logged"/>
    <form @submit.prevent="login" v-else>
      <input type="text" placeholder="Login" v-model="name"/>
      <br/>
      <input type="password" placeholder="******" v-model="password"/>
      <br/>
      <input type="submit" value="Log in"/>
      <input type="button" value="Register" @click="register"/>
      <br/>
      <div v-if="!success" style="color: red">Cannot log in</div>
    </form>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    name: 'Session',
    methods: {
      login () {
        axios('/login', {
          params: {
            username: this.name,
            password: this.password
          },
          method: 'POST'
        }).then(response => {
          this.$emit('login');
          this.logged = true;
          this.success = true;
        }).catch(error => {
          this.success = false;
        });
      },
      logout () {
        axios('/login', {
          params: {
          },
          method: 'POST'
        }).then(response => {
        }).catch(error => {
          console.log(error)
        });
        this.$emit('logout');
        this.logged = false;
        this.success = true;
      },
      register () {
        axios('/register', {
          params: {
            username: this.name,
            password: this.password
          },
          method: 'POST'
        }).then(response => {
          this.login();
        }).catch(error => {
          console.log(error)
        });
      }
    },
    data() {
      return {
        logged: false,
        name: '',
        password: '',
        success: true
      }
    }
  }
</script>
