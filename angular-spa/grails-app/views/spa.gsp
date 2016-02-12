<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Welcome to Grails</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <style type="text/css">
        [ng\:cloak], [ng-cloak], [data-ng-cloak], [x-ng-cloak], .ng-cloak, .x-ng-cloak {
            display: none !important;
        }
    </style>

    <asset:stylesheet src="application.css"/>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

    <script type="text/javascript">
        window.contextPath = "${request.contextPath}";
    </script>
</head>

<body ng-app="angular.spa" ng-controller="IndexController as vm">

    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
        </div>
    </div>

    <div id="content" role="main">
        <section class="row colset-2-its">
            <h1>Welcome to the SPA created with Grails & AngularJS</h1>

            <p>
            Next?
            </p>

            <div id="controllers" role="navigation">
                <ul ng-cloak>
                	<li><a ng-href="{{vm.contextPath}}/sourcesFile">Sources</a></li>
                </ul>
            </div>
        </section>
    </div>

    <div class="footer" role="contentinfo"></div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>
    <source-file></source-file>
    <asset:javascript src="/angular/spa/angular.spa.js" />
</body>
</html>
