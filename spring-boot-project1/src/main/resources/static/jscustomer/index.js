require(
    ['/project1/jscustomer/GlobleConfig.js'],
    function(){
        requirejs(
            ['jquery','bootstrap','custom'],
            function($){
                //start 该处定义我们自己的脚本


                //end 该处定义我们自己的脚本
            }
        )
    }
);