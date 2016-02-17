//= wrapped

angular
    .module("angular.spa.index")
    .directive("sourceFile", applicationData);

function applicationData() {
    var directive = {
        restrict: "EA",
        templateUrl: "/angular/spa/index/spa.html",
        //controller: "IndexController",
        //controllerAs: "vm",
        transclude: true,
        scope: {},
        /*bindToController: {
        }*/
    };

    return directive;
}
