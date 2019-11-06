const shortUrlApi = Vue.resource('/short{/uuid}');
const statisticApi = Vue.resource('/statistic{/uuid}');

Vue.component('short-url-form', {
    props: ['shortUrl, shortUrlAttr'],
    data: function () {
        return {
            uuid: '',
            originalUrl: '',
        }
    },
    watch: {
        shortUrlAttr: function (newVal, oldVal) {
            this.uuid = newVal.uuid;
            this.originalUrl = newVal.originalUrl;
        }
    },
    template:
        '<div>' +
            '<input type="text"  style=" width: 280px;" placeholder="Enter you url" v-model="originalUrl" />' +
            '<input type="button" value="Shorten" @click="shorten" /> '  +
            '<a :href="uuid" target="_blank"> {{ uuid }} </a>' +
        '</div>',
    methods: {
        shorten: function () {
            const shortUrl = {
                uuid: this.uuid,
                originalUrl: this.originalUrl,
            };

            shortUrlApi.save({}, shortUrl.originalUrl).then(result =>
                result.json().then(data => {
                    this.shortUrl = data;
                    this.originalUrl = data.originalUrl;
                    this.uuid = 'http://' + window.location.host + '/short/' + data.uuid;
            })
            )

        }
    }
});

const app = new Vue({
   el: '#app',
    template: '<short-url-form  :shortUrl="shortUrl" />',
   data: {
       shortUrl: {}
   }
});