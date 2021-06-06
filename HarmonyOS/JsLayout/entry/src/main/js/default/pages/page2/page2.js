import router from '@system.router';
export default {
    data: {
        title: 'World',
        data1:''
    },
    goBack(){
        //回去page
//        router.back()

        //回传参数到page
        router.replace({
            uri: 'pages/page/page',
            params:{
                data2:'回传'
            }
        })
    }
}







