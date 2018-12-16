<template>
  <div style="height: 200px; overflow-y:scroll;">
    <table>
      <colgroup>
        <col style="width: 25%">
        <col style="width: 25%">
        <col style="width: 25%">
        <col style="width: 25%">
      </colgroup>
      <thead>
        <tr>
          <th>
            X
          </th>
          <th>
            Y
          </th>
          <th>
            R
          </th>
          <th>
            Result
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="p in points">
          <td>
            {{p.x}}
          </td>
          <td>
            {{p.y}}
          </td>
          <td>
            {{p.r}}
          </td>
          <td>
            {{p.inside? 'inside' : 'outside'}}
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>

  export default {
    name: 'Results',
    props: {
      points: {
        type: Array,
        default: []
      }
    }
  }
</script>
