<template>
  <div>
    <table>
      <colgroup>
        <col style="width: 10%;">
        <col style="width: 80%;">
        <col style="width: 10%;">
      </colgroup>
      <tr>
        <td>Гуляев Б.С., Полещук Ф. А.</td>
        <td align="center">P3212</td>
        <td>Вариант 938123</td>
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
          <Session @login="logged = true" @logout="logged = false"></Session>
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
      axios('/send', {
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
    }
  },
  beforeMount() {
    axios('/fetch', {
      params: {
      },
      method: 'GET'
    }).then(response => {
      this.points = response.data;
    }).catch(error => {
      this.points = [];
      console.log(error)
    })
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

</style>
