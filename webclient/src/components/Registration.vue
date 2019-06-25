<template>
    <div class="box">
        <h2>Register</h2>
        <form class="form-wrapper" @submit.prevent="register">
            <div id="message">{{ message }}</div>
            <div class="form-group">
                <input type="text" v-model="firstName" name="firstName" placeholder="First Name" required :disabled="disabled"/>
            </div>
            <div class="form-group">
                <input type="text" v-model="lastName" name="lastName" placeholder="Last Name" required :disabled="disabled"/>
            </div>
            <div class="form-group">
                <input type="text" v-model="phone" name="phone" placeholder="Phone" required :disabled="disabled"/>
            </div>
            <div class="form-group">
                    <label>Date of Birth</label>
                    <div class="multicolumn">
                        <select id="month" @change="checkTotalDay()" name="month" v-model="month" :disabled="disabled">
                            <option selected value="">Month</option>
                            <option value='1'>January</option>
                            <option value='2'>February</option>
                            <option value='3'>March</option>
                            <option value='4'>April</option>
                            <option value='5'>May</option>
                            <option value='6'>June</option>
                            <option value='7'>July</option>
                            <option value='8'>August</option>
                            <option value='9'>September</option>
                            <option value='10'>October</option>
                            <option value='11'>November</option>
                            <option value='12'>December</option>
                        </select>
                        <select id="date" name="date" v-model="date" :disabled="disabled">
                            <option selected value="">Date</option>
                            <option v-for="date in checkTotalDay()" v-bind:key="date">{{ date }}</option>
                        </select>
                        <select id="year" name="year" v-model="year" :disabled="disabled">
                            <option selected value="">Year</option>
                            <option v-for="yearOption in getYear()" v-bind:key="yearOption">{{ yearOption }}</option>
                        </select>
                    </div>
                </div>
            <div class="form-group">
                <input type="radio" v-model="gender" name="gender" value="true"> <label>Male</label>
                <input type="radio" v-model="gender" name="gender" value="false"><label>Female</label>
            </div>
            <div class="form-group">
                <input type="text" v-model="email" name="email" placeholder="Email" required :disabled="disabled"/>
            </div>
            <div class="form-group">
                <button type="submit" :disabled="disabled">Register</button>
            </div>
        </form>
        <div class="form-footer">
            Footer
        </div>
    </div>
</template>

<script>
import Registration from '@/server/Registration'
export default {
  name: 'Registration',
  data () {
    return {
      firstName: '',
      lastName: '',
      email: '',
      phone: '',
      month: '',
      date: '',
      year: '',
      gender: true,
      yearOptions: '',
      message: '',
      disabled: false
    }
  },
  methods: {
    async register () {
      const birthDate = this.year + '-' + this.month + '-' + this.date
      this.disabled = true
      console.log(birthDate)
      var redirect = 'Registration'
      await Registration.register({
        phone: this.phone,
        firstName: this.firstName,
        lastName: this.lastName,
        gender: this.gender,
        email: this.email,
        birthDate: birthDate === '--' ? null : birthDate
      }).then(
        (response) => {
          console.log('Success')
          redirect = 'Login'
        },
        (error) => {
          console.log('xxx: ' + error.response.data)
          this.message = error.response.data
          this.disabled = false
        }
      )
      this.$router.push({name: redirect})
    },
    getYear () {
      const year = new Date().getFullYear()
      return Array.from({length: year - 1947}, (value, index) => 1948 + index)
    },
    checkTotalDay () {
      var year = this.year
      var month = this.month
      console.log(this.month)
      console.log(this.year)
      var totalDate = 31
      if (year !== '' && month !== '') {
        totalDate = new Date(year, month, 0).getDate()
      }
      return totalDate
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

.form-group input[type="text"]:disabled {
    width: 100%;
    display: block;
    margin: 15px auto;
    box-sizing: border-box;
    padding: 10px 14px;
    opacity: .5;
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

.form-group button:disabled {
    width: 100%;
    margin: 15px auto;
    padding: 10px 15px;
    background-color: purple;
    color: white;
    font-size: 15px;
    border-radius: 8px;
    opacity: .5;
}

.form-footer {
    background-color: purple;
    color: white;
    text-align: center;
    padding: 30px 15px;
    margin: 15px auto;
}
</style>
