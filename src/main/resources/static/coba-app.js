var cobaApp = angular.module('CobaApp', []);

cobaApp.controller('CobaController', 
	function($scope) {
		$scope.daftarNama = [];

		$scope.tambah = function() {
			$scope.daftarNama.push($scope.nama);
		}
	});

cobaApp.controller('ApiController', 
	    function($scope, $http, $window) {
	    	$scope.daftarMahasiswa = [];

	    	$scope.updateData = function() {
	    		$http.get('/daftar-mahasiswa')
	    			.then(sukses, gagal);

	    		function sukses(response) {
	    			console.log(response);
	    			$scope.daftarMahasiswa = response.data;
	    		};	

	    		function gagal(response) {
	    			console.log(response);
	    		};
	    	};

	    	$scope.ubah = function(mhs) {
	    		//console.log(mhs);
	    		$window.location.href = "/edit?nim=" + mhs.nim;
	    	};

	    	$scope.hapus = function(mhs) {
	    		$http.delete('/hapus/' + mhs.nim).then(sukses, gagal);

	    		function sukses(response) {
	    			$scope.updateData();
	    		};

	    		function gagal(response) {};
	    	};

	    	$scope.keluar = function(){
	    		$http.get('/logout').then(sukses, gagal);

	    		function sukses(response){
	    			$window.location.href = "/login";
	    		}
	    		function gagal(response){}
	    	};

	    	$scope.updateData();
		}
	);
