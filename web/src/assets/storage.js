import jwt from 'jsonwebtoken'

let storage = {
  set(k, v) {
    localStorage.setItem(k, JSON.stringify(v))
  },
  get(k) {
    return localStorage.getItem(k)
  },
  remove(k) {
    localStorage.removeItem(k)
  },
  parse(k) {
    return jwt.decode(localStorage.getItem(k))
  },
  setUser(v) {
    localStorage.setItem('token', JSON.stringify(v))
  },
  getUser() {
    return jwt.decode(localStorage.getItem('token'))
  },
  removeUser() {
    localStorage.removeItem('token')
  }
}

export default storage;
