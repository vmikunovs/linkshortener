var shortUrlApi = Vue.resource('/api{/url}');

Vue.component('short-url-form', {
    props: ['shortUrl, shortUrlAttr'],
    data: function () {
        return {
            id: '',
            url: '',
            originalUrl: '',
        }
    },
    watch: {
        shortUrlAttr: function (newVal, oldVal) {
            this.url = newVal.url;
            this.originalUrl = newVal.originalUrl;
        }
    },
    template:
        '<div>' +
            '<input type="text"  style=" width: 280px;" placeholder="Enter you url" v-model="originalUrl" />' +
            '<input type="button" value="Shorten" @click="shorten" /> '  +
            '<a :href="url"> {{ url }} </a>' +
        '</div>',
    methods: {
        shorten: function () {
            const shortUrl = {
                url: this.url,
                originalUrl: this.originalUrl,
            };

            shortUrlApi.save({}, shortUrl.originalUrl).then(result =>
                result.json().then(data => {
                    this.shortUrl = data;
                    this.originalUrl = data.originalUrl;
                    this.url = window.location.host + '/api/' + data.url;
                //this.originalUrl = data.originalUrl;
            })
            )

        }
    }
});

var app = new Vue({
   el: '#app',
    template: '<short-url-form  :shortUrl="shortUrl" />',
   data: {
       shortUrl: {}
   }
});