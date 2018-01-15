cobaApp.controller('EditController', 
	    function($scope, $http, $window) {

	$scope.mhs = {};

	$scope.simpan = function() {
		//console.log($scope.mhs);
		$http.post('/simpan', $scope.mhs).then(sukses, gagal);

		function sukses(response) {
			$window.location.href = "/";
		};

		function gagal(response) {
			console.log(response);
		};
	};

	$scope.batal = function() {
		$window.location.href = "/";
	};

	$scope.muat = function() {
		var nim = $window.location.search.split("=")[1];
		$scope.mhs.nim = nim;

		$http.get('/ambil-data-mhs/' + nim).then(sukses, gagal);

		function sukses(response) {
			//console.log(response.data);
			$scope.mhs = response.data;
		};

		function gagal(response) {};

	};

	$scope.muat();

});