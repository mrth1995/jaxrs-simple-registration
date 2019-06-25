import Api from '@/services/Api'

export default {
  register (param) {
    return Api().post('user', param)
  }
}
