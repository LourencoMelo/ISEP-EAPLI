var cols = 20;
var rows = 18;
var agvDocksQnt = 6;

var colors = [];
var agvDock = [];

function makeMatrix(cols, rows) {
    var arr = new Array(cols);
    for (var i = 0; i < arr.length; i++) {
        arr[i] = new Array(rows);
    }

    return arr;
}

function setup() {
    createCanvas(600, 540);
    for (var i = 0; i < cols; i++) {
        colors = makeMatrix(cols, rows);
        for (var j = 0; j < rows; j++) {
            colors[i][j]=0;
        }
    }
    /**
     * AGV Docks
     */
    colors[0][2] = 1;
    colors[0][4] = 1;
    colors[0][12] = 1;
    colors[0][14] = 1;
    colors[19][3] = 1;
    colors[19][13] = 1;

    /**
     * Acessibility
    */
    colors[8][1] = 2;
    colors[8][2] = 2;

    colors[5][5] = 2;
    colors[5][6] = 2;

    colors[14][11] = 2;
    colors[14][12] = 2;

    colors[10][15] = 2;
    colors[10][16] = 2;

    setTimeout(setup, 1000);
    
}

function draw() {
    background(250);

    for (var i = 0; i < cols; i++) {
        for (var j = 0; j < rows; j++) {
            var x = i * 30;
            var y = j * 30;
            /**
             * Aisles
             */
            if((i>=4 && i <= 15 && j ==0)||
            (i>= 4 && i<=15 && j>=7 && j<=8)||
            (i>= 4 && i<=15 && j>=9 && j<=10)||
            (i>=4 && i <=15 && j==17)){
                fill(163, 163, 158);
                stroke(0);
                rect(x,y, 30, 30);
            }else{
                /**
                 * Funciona pra baixo tirar dos ifs grandes
                 */
                if(colors[i][j] == 1){
                    fill(252, 235, 3);
                }else if(colors[i][j] == 2){
                    fill(3, 177, 252)
                }else{
                    fill(255);
                }
                stroke(0);
                rect(x, y, 30, 30); 
            }
        }
    }
    setTimeout(draw, 1000);
}