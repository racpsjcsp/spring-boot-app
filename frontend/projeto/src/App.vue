<template>
  <div id="app">
    <p>Novo Usuário:
      <input type="text" v-model="novoUsuario">
      <button @click="adicionarUsuario">Adicionar</button>
    </p>
    <ul>
      <li v-for="(usuario, index) in usuarios" @click="excluirUsuario(index)" :key="index">
        {{usuario.nome}}
      </li>
      
    </ul>
  </div>
</template>

<script>
export default {
  name: 'app',
  data() {
    return {
      novoUsuario: '',
      usuarios: [
        { nome: 'Joao',
          email: 'joao@gmail.com',
          nickname: 'jo',
          senha: 'teste',
          autorizacao: 'USER'
        },
        { nome: 'Maria',
          email: 'maria@gmail.com',
          nickname: 'mary',
          senha: 'teste',
          autorizacao: 'USER'        
        }
      ]
    }
  },
  methods: {
    adicionarUsuario() {
      if (this.novoUsuario.trim().length== 0) {
        alert('O nome do usuário deve ser preenchido!')
      } else { 
        this.usuarios.push( { nome: this.novoUsuario} )
      }
    },
    excluirUsuario(indice) {
      this.usuarios.splice(indice, 1)
    }
  }
}</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
