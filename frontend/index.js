var app = new Vue({
  el: '#app',
  data: {
  	contador: 0
  },
  	computed: {
  		contadorDobro: {
  			get() {
  				return this.contador * 2
  			},
  			set(valor) {
  				this.contador = Math.floor(valor/2)
  			}
  		}
  	}
 })