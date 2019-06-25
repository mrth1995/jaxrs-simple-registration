<template>
    <div class="box">
        <h2>Login</h2>
        <form class="form-wrapper" @submit.prevent="register">
            <div id="message">{{ message }}</div>
            <div class="form-group">
                <input type="text" v-model="email" name="email" placeholder="Email" required/>
            </div>
            <div class="form-group">
                <input type="text" v-model="password" name="password" placeholder="Password" required/>
            </div>
            <div class="form-group">
                <button type="submit">Login</button>
            </div>
        </form>
        <div class="form-footer">
            Footer
        </div>
    </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      email: '',
      password: '',
      message: ''
    }
  }
}
</script>

<style type="text/css">
body {
    padding: 0;
    margin: 0;
    font-family: sans-serif;
}

.box {
    width: 500px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}

#message {
    background: red;
    width: 100%;
}

.form-wrapper {
    background: #d1d1d1;
    padding: 15px;
    border-radius: 8px;
}

.form-wrapper > h1 {
    font-weight: 500;
}

.form-group input[type="text"] {
    width: 100%;
    display: block;
    margin: 15px auto;
    box-sizing: border-box;
    padding: 10px 14px;
}

.form-group label {
    font-size: 10pt;
    margin: 15px auto;
}

.form-group select {
    display: inline;
    margin: 15px auto;
    padding: 10px 14px;
}

.form-group button {
    width: 100%;
    margin: 15px auto;
    padding: 10px 15px;
    background-color: purple;
    color: white;
    font-size: 15px;
    border-radius: 8px;
}

.form-footer {
    background-color: purple;
    color: white;
    text-align: center;
    padding: 30px 15px;
    margin: 15px auto;
}
</style>
