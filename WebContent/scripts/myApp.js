		var app = angular.module('myApp', ["ngRoute"]);
		
		app.config(function($routeProvider) {
			$routeProvider.when("/", {
				templateUrl : "loginPage.html",
				controller : "userCtrl"
			}).when("/coursesId", {
				templateUrl : "photoshop.html",
				controller : "courseCtrl"
			}).when("/lecture", {
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
		var courseId;
		var userId;
		var userType;
		
		app.controller('homePage', function($scope, $http,$location,$rootScope,$anchorScroll) {
			
			 $scope.gotoBottom = function(id){
				 var newHash = 'anchor' + x;
			      if ($location.hash() !== newHash) {
			        $location.hash('anchor' + x);
			      } else {
			        $anchorScroll();
			      }
			 }
			    
			    
			$scope.logout=function(){
				if(confirm("log out?")== true){
					$location.path("/");
					$(".body1").hide();
				}
			}
			
			
			 $scope.associate = function(id){
					$http.get("http://localhost/coursemanagment/rest/coursesService/getAssociatedCoursesWithSubject?CourseSubject="+id)
					.then(function(response) {
						$scope.associated = response.data;
						
						console.log($scope.associated);
						if($scope.associated == null){
							alert("Thers are NO assocoate courses in this subject");
							}else{
							}
					 })
				}
			 
			 $http.get("/coursemanagment/rest/courseSubjectService/getAllCourseSubject")
				.then(function(response) {
					$scope.allsubject = response.data;
					$scope.allSubjectBtn = true;
					$rootScope.allSubjectBtnHide = $scope.allSubjectBtnHide;
					$rootScope.allSubjectBtn = $scope.allSubjectBtn;
					$rootScope.courseDetailesBtn = $scope.courseDetailesBtn;
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
					userId = $scope.userim.id;
					userType= $scope.userim.type;
						$(".body1").show();
						$location.path('/studentPage');
					
					}else if ($scope.userim.type == "lectur") {
						userId = $scope.userim.id;
						$(".body1").show();
						/*$location.path('/--LecturPage!!--.html');*/
						alert("lectur");
						
					}else if ($scope.userim.type == "admin") {
						$(".body1").show();
						$location.path('/managerPage');
					}
					

					$http.get("http://localhost/coursemanagment/rest/student/getStudentNameByUserId?id="+userId).
					then(function(response){
						$scope.usersName = response.data;
					});
				});
			}
			
		});
		
		
		app.controller('courseCtrl' , function($scope,$http,$location,$rootScope){
			
			$rootScope.courseDetailesBtn = true;
			$rootScope.allSubjectBtnHide = true;
			
			$scope.returnBackToStudentPage = function (){
				$rootScope.courseDetailesBtn = false;
				$rootScope.allSubjectBtnHide = false;
				if(userType == "student"){
					$location.path("/studentPage");
				}
				if(userType == "admin"){
					$location.path("/managerPage");
				}
				
			}
			
			
			$http.get("http://localhost/coursemanagment/rest/coursesService/getCoursesById?id="+courseId)
			.then(function(response) {
				$scope.courseDetailes = response.data;
				console.log($scope.courseDetailes);
			
			

				$http.get("http://localhost/coursemanagment/rest/daysService/getDaysAssociateToCourseById?id="+courseId)
				.then(function(response) {
					$scope.courseDays= response.data;
					console.log($scope.courseDays);
			
			
					$http.get("http://localhost/coursemanagment/rest/chatService/getAllMassages?id="+courseId)
					.then(function(response) {
						$scope.allMassages = response.data;
						
					});
					
				});
			});
			
			$scope.sendMassage = function(){
			var d = new Date();
			var date1 = d.getFullYear();
			var date2 = d.getMonth()+1;
			var date3 = d.getDate();
			var date4 = d.getHours();
			var date5 = d.getMinutes();
			var date6 = d.getSeconds();
			
			var date = date1+"-"+date2+"-"+date3+" "+date4+":"+date5+":"+date6;
			
			console.log(date)
			
			$http.get("/coursemanagment/rest/chatService/createNewMassage?"+
					"course="+courseId+"&date="+date+"&massage="+$scope.massageText).then(function(response){
						$scope.result = response.data;
						if($scope.result != null)
						{
							$scope.massageText='';
							alert('massage sent !');
							
							$http.get("http://localhost/coursemanagment/rest/chatService/getAllMassages?id="+courseId)
							.then(function(response) {
								$scope.allMassages = response.data;
								console.log($scope.allMassages);
							});
							
						}
			});
			
			
			}
			
			
		})
		
		app.controller('studentCtrl', function($scope, $http,$location,$rootScope) {
			if(userType=="student"){
				$("#newSubject").hide();
				$("#newSubject1").hide();
			}
			
				$scope.getCourseDetailes = function(id){
					$rootScope.allSubjectBtn = false;
					alert(id);
					courseId = id;
					$location.path("/coursesId");
				}
		});
		
		
		app.controller('managerCtrl', function($scope, $http,$location,$rootScope) {
			
			$scope.allCourses = true;
			$scope.associatedCourses = false;
			$scope.corseDetailesModal = true;
			
			$rootScope.corseDetailesModal = $scope.corseDetailesModal;
			$rootScope.corseDetailesModalShow = $scope.corseDetailesModalShow;
			
			$scope.getCourseDetailes = function(id){
				$rootScope.corseDetailesModalShow = true;
				alert(id);
				
				$http.get("/coursemanagment/rest/CourseMembers/getCourseMembersByCourseId?id="+id)
				.then(function(response) {
					$scope.courseMembers = response.data;
					console.log($scope.courseMembers);
				});
				
			}
			
			$http.get("/coursemanagment/rest/courseSubjectService/getAllCourseSubject")
			.then(function(response) {
				$scope.allsubject = response.data;
			});
			
			  
			$http.get("http://localhost/coursemanagment/rest/coursesService/ActiveCourse")
			.then(function(response) {
				$scope.allCourses = response.data;
				
				 $http.get("http://localhost/coursemanagment/rest/lecturers/getAllLecturers")
					.then(function(response) {
						alert("eee");
						$scope.allLecturers = response.data;
						
					console.log($scope.allLecturers);
					
					
					 $http.get("http://localhost/coursemanagment/rest/courseSubjectService/getAllCourseSubject")
						.then(function(response) {
							$scope.allCourseSubject = response.data;
							console.log($scope.allCourseSubject);
							
						 $http.get("http://localhost/coursemanagment/rest/rooms/getAllRooms")
							.then(function(response) {
								$scope.allRooms = response.data;
								console.log($scope.allRooms);
						});
					});
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
			 
			 $scope.edit = function(x) {
				 $scope.courseid = $scope.allCourses[x].id;
				 $scope.upcourseName = $scope.allCourses[x].courseName;				 
				 $scope.uplecture =$scope.allCourses[x].lecturer.firstName;
				 $scope.upcourseSubject= x.courseSubject;
				 $scope.uproom = $scope.allCourses[x].rooms.roomName;
				 $scope.updescription = $scope.allCourses[x].description;
				 $scope.upisActive = $scope.allCourses[x].active;
			
					/* $location.path('/updateCourse');*/
				
				 }
			 console.log($scope.courseid);
			 console.log($scope.upcourseName);
			 console.log($scope.updescription);
			 console.log($scope.upisActive);
			
			 
			 
				
			 $scope.updateCourse = function(x) {
				 $scope.upstartDate = $(".upstartdate").val();
				 $scope.upenDate = $(".upenddate").val();
				 console.log($(".upstartdate").val());
				 console.log($(".upenddate").val());
				 console.log($scope.uplecture);
				 console.log($scope.upcourseSubject);
				 console.log($scope.uproom);
				 if(!( $scope.upcourseName==undefined||$scope.uplecture==undefined||$scope.upcourseSubject==undefined||$scope.uproom==undefined||
					$scope.updescription==undefined||$scope.upisActive==undefined||$scope.upstartDate==undefined||$scope.upenDate==undefined)){
					 $http.get("http://localhost/coursemanagment/rest/coursesService/updateCorses?id="+$scope.courseid+"&courseName="+$scope.upcourseName
							 +"&lecturer="+$scope.uplecture+"&startdate="+$scope.upstartDate+"&enddate="+$scope.upenDate+"&description="+$scope.updescription
							 +"&courseSubject="+$scope.upcourseSubject+"&room="+$scope.uproom+"&isActive="+$scope.upisActive)
						.then(function(response) {
							$scope.updateResponse = response.data;
						console.log($scope.updateResponse);
						if($scope.updateResponse=="OK"){
							$http.get("http://localhost/coursemanagment/rest/coursesService/ActiveCourse")
							.then(function(response) {
								$scope.allCourses = response.data;
							});
							$scope.resrartCourse();
						}else{
							alert("update faild please try agine");
						}
						});
				 }else{
					 alert("All fields must be complete to update a course");
				 }
			 }
			 $scope.resrartCourse = function() {
				 $scope.upcourseName==undefined;
				 $scope.uplecture==undefined;
				 $scope.upcourseSubject==undefined;
				 $scope.uproom==undefined;
				 $scope.updescription==undefined;
				 $scope.upisActive==undefined;
				 $scope.upstartDate==undefined;
				 $scope.upenDate==undefined;
				 $scope.addcourseName==undefined;
				 $scope.addlecture==undefined;
				 $scope.addcourseSubject==undefined;
				 $scope.addroom==undefined;
				 $scope.adddescription==undefined;
				 $scope.addisActive==undefined;
				 $scope.addstartDate==undefined;
				 $scope.addenDate==undefined;
				 
			 }
	
			 $scope.addCourse = function() {
				 $scope.addstartDate = $(".addstartdate").val();
				 $scope.addenDate = $(".addenddate").val();
				 if(!( $scope.addcourseName==undefined||$scope.addlecture==undefined||$scope.addcourseSubject==undefined||$scope.addroom==undefined||
							$scope.adddescription==undefined||$scope.addisActive==undefined||$scope.addstartDate==undefined||$scope.addenDate==undefined)){
							 $http.get("http://localhost/coursemanagment/rest/coursesService/createNewCourse?&courseName="+$scope.addcourseName
									 +"&lecturer="+$scope.addlecture+"&startdate="+$scope.addstartDate+"&enddate="+$scope.addenDate+"&description="+$scope.adddescription
									 +"&courseSubject="+$scope.addcourseSubject+"&room="+$scope.addroom+"&isActive="+$scope.addisActive)
								.then(function(response) {
									$scope.addResponse = response.data;
								console.log($scope.updateResponse);
								if($scope.addResponse!=null){
									$http.get("http://localhost/coursemanagment/rest/coursesService/ActiveCourse")
									.then(function(response) {
										$scope.allCourses = response.data;
									});
									$scope.resrartCourse();
								}else{
									alert("update faild please try agine");
								}
								});
						 }else{
							 alert("All fields must be complete to create a course");
						 }
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
								alert("Try again");
							}
							
						});
					  
					  
				  }
		
		});