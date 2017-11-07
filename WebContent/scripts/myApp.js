		var app = angular.module('myApp', ["ngRoute"]);
		
		app.config(function($routeProvider) {
			$routeProvider.when("/", {
				templateUrl : "loginPage.html",
				controller : "userCtrl"
			}).when("/student", {
				templateUrl : "templateUrl.html",
				controller : "userCtrl"
			}).when("/lectur", {
				templateUrl : "templateUrl.html",
				controller : "controller"
			}).when("/admin", {
				templateUrl : "templateUrl.html",
				controller : "managerCtrl"
			}).when("/studentPage", {
				templateUrl : "studentPage.html",
				controller : "studentCtrl"
			}).when("/managerPage", {
				templateUrl : "managerPage.html",
				controller : "managerCtrl"		
			
			});
		});
		
		
		app.controller('userCtrl', function($scope, $http,$location) {
			
			$scope.submit = function() {
				$http.get("http://localhost/coursemanagment/rest/usersService/getUser?username="+$scope.username +"&password="+$scope.password)
				.then(function(response) {
					$scope.userim = response.data;
					if ($scope.userim.type == null) {
						alert("Username or password incorrect");
						
					}else if ($scope.userim.type == "student") {
						alert("student");
						$(".body1").show();
						$location.path('/studentPage');
					
					}else if ($scope.userim.type == "lectur") {
						alert("lectur");
						$(".body1").show();
						/*$location.path('/--LecturPage!!--.html');*/
						alert("lectur");
						
					}else if ($scope.userim.type == "admin") {
						$(".body1").show();
						$location.path('/managerPage');
						alert("admin");
					}
				});
			}
		});
		
		app.controller('studentCtrl', function($scope, $http) {
			$http.get("http://localhost/coursemanagment/rest/courseSubjectService/getAllCourseSubject")
			.then(function(response) {
				$scope.allsubject = response.data;
				console.log($scope.allsubject);
			});
			
			
			  $scope.associate = function(id){
				
			$http.get("http://localhost/coursemanagment/rest/coursesService/getAssociatedCoursesWithSubject?CourseSubject="+id)
			.then(function(response) {
				$scope.associated = response.data;
				console.log($scope.associated);
				if($scope.associated == null){
					alert("Thers are NO courses in this subject");

					}else{
						
						
					}
			
			 })
			}
			  
			
		});
		app.controller('managerCtrl', function($scope, $http,$location) {
			$http.get("http://localhost/coursemanagment/rest/coursesService/ActiveCourse")
			.then(function(response) {
				$scope.allCourses = response.data;
				
				 $http.get("http://localhost/coursemanagment/rest/courseSubjectService/getAllCourseSubject")
					.then(function(response) {
						$scope.allsubject = response.data;
						console.log($scope.allsubject);
					});
					
				
			});
			
			
			
			/*  remove the course */
			 $scope.remove = function(x) {
				 if(confirm("you sure you whant to delete this course?")==true){
				 $http.get("http://localhost/coursemanagment/rest/coursesService/ArchiveCourse?id="+$scope.allCourses[x].id+"&isActive=false")
					.then(function(response) {
						$scope.removecourse = response.data;
						console.log($scope.removecourse);
						if($scope.removecourse=="OK"){
							$http.get("http://localhost/coursemanagment/rest/coursesService/ActiveCourse")
							.then(function(response) {
								$scope.allCourses = response.data;
							});
						}else{
							alert("Action failed Please try again");
						}
					});
					}
			 }
			 
				  $scope.associate = function(id){
					
				$http.get("http://localhost/coursemanagment/rest/coursesService/getAssociatedCoursesWithSubject?CourseSubject="+id)
				.then(function(response) {
					$scope.associated = response.data;
					$location.path("/managerPage");
					console.log($scope.associated);
					if($scope.associated == null){
						alert("Thers are NO courses in this subject");

						}
						
				 })
				}
				  
				  $scope.addNewSubject = function(newSubject){
					  
					  $http.get("http://localhost/coursemanagment/rest/courseSubjectService/createNewCourseSubject?subject="+$scope.newSubject)
						.then(function(response) {
							$scope.result = response.data;
							console.log($scope.result );
							
							
							if ($scope.result != null){
								
								alert("Subject added to list.");
								
								$http.get("http://localhost/coursemanagment/rest/courseSubjectService/getAllCourseSubject")
								.then(function(response) {
									$scope.allsubject = response.data;
									console.log($scope.allsubject);
								});
								
							}else{
								alert("Try again .");
							}
							
						});
					  
					  
				  }
		
		});