<script setup lang="ts"></script>

<template>
  <v-card class="mx-auto" width="1280">
    <v-container>
      <v-row>
        <v-col md="4" sm="6" xs="12">
          <v-skeleton-loader
            width="250"
            height="200"
            type="image"
            class="mx-auto"
          ></v-skeleton-loader>
        </v-col>
        <v-col md="4" sm="6" xs="12" v-for="i in 2" :key="i">
          <v-skeleton-loader
            width="150"
            min-width="100"
            max-width="250"
            height="30"
            type="list-item"
          ></v-skeleton-loader>
          <v-skeleton-loader
            v-for="i in 3"
            :key="i"
            min-width="100"
            max-width="250"
            height="57"
            type="list-item-avatar"
          ></v-skeleton-loader>
        </v-col>
      </v-row>
    </v-container>

    <v-card-title>
      <v-skeleton-loader height="33" type="heading" l></v-skeleton-loader>
    </v-card-title>

    <v-card-subtitle>
      <v-skeleton-loader type="subtitle"></v-skeleton-loader>
    </v-card-subtitle>

    <v-card-actions>
      <v-skeleton-loader width="250" type="actions"></v-skeleton-loader>
      <v-spacer></v-spacer>
    </v-card-actions>
  </v-card>
</template>

<style scoped></style>
