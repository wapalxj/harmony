import router from '@system.router';
export default {
    data: {
        title: 'World',
        data2: ''
    },
    jumpTo(){
        router.push ({
        //跳转到page2
            uri: 'pages/page2/page2',
            params:{
                data1:'vero'
            }
        });
    }

}
