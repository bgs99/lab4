<template>
  <div class="grid-root">
    <table>
      <colgroup>
        <col style="width: 10%;">
        <col style="width: 80%;">
        <col style="width: 10%;">
      </colgroup>
      <tr>
        <td><div id="names">Гуляев Б.С., Полещук Ф. А.</div></td>
        <td align="center"><div id="group" align="center">P3212</div></td>
        <td><div id="var">Вариант 938123</div></td>
      </tr>
    </table>
    <table>
      <tr>
        <td v-if="logged" >
          <table>
            <tr>
              <td>
                <Map :r="r" :points="points" @update:click="processClick"></Map>
              </td>
              <td>
                <Controls :r="r" @update:r="r = $event" @update:points="points.unshift($event)"></Controls>
              </td>
              <td>
              </td>
            </tr>
          </table>
          <br/>
          <Results :points="points"></Results>
        </td>
        <td style="vertical-align: top">
          <Session @login="login" @logout="logout"></Session>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
  import axios from 'axios'

  import Map from './components/Map'
  import Controls from './components/Controls'
  import Results from "./components/Results";
  import Session from "./components/Session";


  export default {
  name: 'App',
  components: {
    Session,
    Results,
    Map,
    Controls
  },
  methods: {
    send (x, y) {
      axios('/api/send', {
        params: {
          x: x,
          y: y,
          r: this.r
        },
        method: 'POST'
      }).then(response => {
        this.points.unshift({
          x: x,
          y: y,
          r: this.r,
          inside: response.data
        });
      }).catch(error => {
        this.points.unshift({
          x: x,
          y: y,
          r: this.r,
          inside: false
        });
        console.log(error)
      })
    },
    processClick (event) {
      this.send((event.offsetX-150)/100*this.r, (150-event.offsetY)/100*this.r);
    },
    login () {
      axios('/api/fetch', {
        params: {
        },
        method: 'GET'
      }).then(response => {
        this.points = response.data;
      }).catch(error => {
        this.points = [];
        console.log(error)
      });
      this.logged = true;
    },
    logout () {
      this.points = [];
      this.logged = false;
    }
  },
  data () {
    return {
      r: 1,
      points: [],
      logged: false
    }
  }
}
</script>

<style>
  .grid-root {
    display: grid;
    grid-template-columns: 10% 30% 40% 10% 10%;
    grid-template-rows: 10% 90%;
  }
  .login {
    grid-row: 2;
  }
  .login-right {
    grid-column: 4/span 2;
  }
  .header {
    grid-row: 1
  }
  #names {
    grid-column: 1;
  }
  #group {
    grid-column: 2;
  }
  #var {
    grid-column: 3;
  }
</style>
