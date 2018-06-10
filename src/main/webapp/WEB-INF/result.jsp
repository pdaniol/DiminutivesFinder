<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Diminutives Finder</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons">

    <link rel="stylesheet"
          href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css"
          integrity="sha384-wXznGJNEXNG1NFsbm0ugrLFMQPWswR3lds2VeinahP8N0zJw9VWSopbjv2x7WCvX" crossorigin="anonymous">

</head>
<body>

<div class="jumbotron jumbotron-dark text-center">
    <div class="row">
        <div class="col-8 offset-2">
            <h1>Diminutives Finder</h1>

            <div id="chartContainer" style="height: 370px; width: 100%;margin-top: 20px;margin-bottom: 20px"></div>

            <p>
                <a class="btn btn-primary" data-toggle="collapse" href="#collapseDiminutives" role="button"
                   aria-expanded="false" aria-controls="collapseExample">
                    Zdrobnienia
                </a>
                <button class="btn btn-primary" type="button" data-toggle="collapse"
                        data-target="#collapseNonDiminutives"
                        aria-expanded="false" aria-controls="collapseExample">
                    Wyrazy niezdrobniałe
                </button>
            </p>
            <div class="collapse" id="collapseDiminutives">
                <div class="card card-body">
                    <c:forEach items="${diminutives}" var="item" varStatus="loop">
                        ${item}${!loop.last ? ', ' : ''}
                    </c:forEach>
                </div>
            </div>
            <div class="collapse" id="collapseNonDiminutives">
                <div class="card card-body">
                    <c:forEach items="${nonDiminutives}" var="item" varStatus="loop">
                        ${item}${!loop.last ? ', ' : ''}
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>




<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://unpkg.com/popper.js@1.12.6/dist/umd/popper.js"
        integrity="sha384-fA23ZRQ3G/J53mElWqVJEGJzU0sTs+SvzG8fXVWP+kJQ1lwFAOkcUOysnlKJC33U"
        crossorigin="anonymous"></script>
<script src="https://unpkg.com/bootstrap-material-design@4.1.1/dist/js/bootstrap-material-design.js"
        integrity="sha384-CauSuKpEqAFajSpkdjv3z9t8E7RlpJ1UP0lKM/+NdtSarroVKu069AlsRPKkFBz9"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

<script>
    window.onload = function () {

        CanvasJS.addColorSet("greenShades",
            [//colorSet Array

                "#3700B3",
                "#90EE90",
                "#6200EE",
                "#2E8B57",
                "#3CB371"

            ]);


        var chart = new CanvasJS.Chart("chartContainer", {
            animationEnabled: true,
            colorSet: "greenShades",
            title:{
                text: "Zdrobnienia vs. wyrazy niezdrobniałe",
                horizontalAlign: "left"
            },
            data: [{
                type: "doughnut",
                startAngle: 60,
                indexLabelFontSize: 17,
                indexLabel: "{label} - #percent%",
                toolTipContent: "<b>{label}:</b> {y} (#percent%)",
                dataPoints: [
                    { y: ${diminutivesCount}, label: "Zdrobnienia" },
                    { y: ${nonDiminutivesCount}, label: "Wyrazy niezdrobniałe"},
                ]
            }]
        });
        chart.render();

    }
</script>

<script>$(document).ready(function () {
    $('body').bootstrapMaterialDesign();
});</script>

</body>
</html>
