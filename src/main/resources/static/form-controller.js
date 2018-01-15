cobaApp.controller('FormController', 
	    function($scope, $http, $window) {

	$scope.simpan = function() {
		//console.log($scope.mhs.nim);
		//console.log($scope.mhs.nama);
		//console.log($scope.mhs.jurusan);
		$http.post('/simpan', $scope.mhs).then(sukses, gagal);

		function sukses(response) {
			$window.location.href = '/';
		}

		function gagal(response) {}
	}

	$scope.batal = function() {
		$window.location.href = '/';
	}

});