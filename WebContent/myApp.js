		var app = angular.module('myApp', ['ngRoute']);
		
		app.config(function($routeProvider) {
			$routeProvider.when("/", {
				templateUrl : "loginPage.html",
				controller : "userCtrl"
			}).when("/coursesId", {
				templateUrl : "photoshop.html",
				controller : "courseCtrl"
			}).when("/lectur", {
				templateUrl : "lecturPage.html",
				controller : "lecturCtrl"
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
		
app.factory('myService',function($http){
			
			var getData = {};
			
			getData.ActiveCourse = function(){
				return $http.get("http://localhost/coursemanagment/rest/coursesService/ActiveCourse");
			}
			,
			getData.associate = function(id){
				return $http.get("http://localhost/coursemanagment/rest/coursesService/getAssociatedCoursesWithSubject?CourseSubject="+id);
			}
			,
			getData.getAllCourseSubject = function(){
				return $http.get("/coursemanagment/rest/courseSubjectService/getAllCourseSubject");
			}
			,
			getData.getCoursesById = function(courseId){
			return $http.get("http://localhost/coursemanagment/rest/coursesService/getCoursesById?id="+courseId)
			}
			,
			getData.getDaysAssociateToCourseById = function(courseId){
			return $http.get("http://localhost/coursemanagment/rest/daysService/getDaysAssociateToCourseById?id="+courseId)
			}
			,
			getData.getAllMassages = function(courseId){
			return $http.get("http://localhost/coursemanagment/rest/chatService/getAllMassages?id="+courseId)
			}
			,
			getData.ActiveCourse = function(){
		    return $http.get("http://localhost/coursemanagment/rest/coursesService/ActiveCourse")
			}
			,
			getData.getAllLecturers = function(){
			return $http.get("http://localhost/coursemanagment/rest/lecturers/getAllLecturers")
			}
			,
			getData.getAllRooms = function(){
			return $http.get("http://localhost/coursemanagment/rest/rooms/getAllRooms")
			}
			return getData;
			
		});
	
		app.controller('userCtrl', function($scope, $http,$location,$rootScope,myService) {
			
			$scope.submit = function() {
				
				 myService.getAllCourseSubject().then(function(response){
						$scope.allsubject = response.data; 
					 });
				
				$http.get("http://localhost/coursemanagment/rest/usersService/getUser?username="+$scope.username +"&password="+$scope.password)
				.then(function(response) {
					$scope.userim = response.data;
					if ($scope.userim.type == null) {
						
						$("#massage").html("***Invalid email or password***");
						$("#login").effect("shake");
						$("#loginInputs1").addClass("error");
						$("#loginInputs2").addClass("error");
						$("#loginInputs1").focus(function(){
							$("#loginInputs1").removeClass("error");
						})
						$("#loginInputs2").focus(function(){
							$("#loginInputs2").removeClass("error");
						})
						
					}else if ($scope.userim.type == "student")
					{
						
						
						$(".body1").show();
						$("#massage").html("*** Account Validated!!!");
						
						userId = $scope.userim.id;
						
						$rootScope.userId = userId;
						userType= $scope.userim.type;
					
						
						
						$location.path('/studentPage');

						$http.get("http://localhost/coursemanagment/rest/student/getStudentNameByUserId?id="+userId).
						then(function(response){
							$scope.usersName = response.data;
						});
					
					}else if ($scope.userim.type == "lectur")
					{
						$("#massage").html("*** Account Validated!!!");
						$(".body1").show();
						userType= $scope.userim.type;
						userId = $scope.userim.id;
						$location.path('/lectur');
						
					}else if ($scope.userim.type == "admin")
					{
						userType= $scope.userim.type;
						$("#massage").html("*** Account Validated!!!");
						$(".body1").show();
						$location.path('/managerPage');
					}
					

				});
			}
			
		});
		

		
		userId;
		app.controller('homePage', function($scope, $http,$location,$rootScope,myService) {
			$(".cloudup").hide();
			
				if(userType == 'admin'  ){
					$(".cloudup").show();
				}
			 
			$scope.HomePage = function(){
				
				$(".sideBar").show();
				$rootScope.courseDetailesBtn = false;
				$rootScope.allSubjectBtnHide = false;
				
				
				if(userType == "student"){
					$location.path("/studentPage");
				}
				if(userType == "admin"){
					$location.path("/managerPage");
				}
				if(userType == "lectur"){
					$location.path("/lectur");
				}
			}
			///    
				
			userId;
			console.log(userId);
			$scope.userCourses = function(){
				
				$http.get("http://localhost/coursemanagment/rest/CourseMembers/AssociateCoursesToStudentByUserId?userId="+$rootScope.userId)
				.then(function(response){
					$scope.userCourses = response.data;
					console.log($scope.userCourses);
					
					if($scope.userCourses != null){
						$(".UserCourses").show();
						$(".allCourses").hide();
						$(".associated").hide();
					}
				})
				
				
			}
			
			
			$scope.logout=function(){
				if(confirm("log out?")== true){
					$location.path("/");
					$(".body1").hide();
				}
			}
			
			 $scope.associate = function(id)
			 {
						 myService.associate(id).then(function(response){
							 $scope.associated = response.data;
						 },function(resonse){
								$scope.error = response.statusText;
								alert("an error occured" + $scope.error);
								console.log($scope.error);
						});
						
						 
						 
						$rootScope.hideAllCourses = true; 
						
			}
			 
			 myService.getAllCourseSubject().then(function(response){
				$scope.allsubject = response.data; 
			 });
			 
					$scope.allSubjectBtn = true;
					$rootScope.allSubjectBtnHide = $scope.allSubjectBtnHide;
					$rootScope.allSubjectBtn = $scope.allSubjectBtn;
					$rootScope.courseDetailesBtn = $scope.courseDetailesBtn;
		});
		
		app.controller('lecturCtrl' , function($scope,$http,$location,$rootScope,$anchorScroll,myService){

			
			   // wait for the DOM to be loaded 
	        $(document).ready(function() { 
	            // bind 'myForm' and provide a simple callback function 
	            $('#somform').ajaxForm(function() { 
	                alert("Upload successful"); 
	            }); 
	        }); 
			
			$scope.showAllCourses = true;
			$scope.showAssociateCourses = false;
			
			$rootScope.showAssociateCourses = $scope.showAssociateCourses;
			$rootScope.showAllCourses = $scope.showAllCourses ;
			
			
			
			$scope.getCourseDetailesOfStudents = function(id){
				$http.get("/coursemanagment/rest/CourseMembers/getCourseMembersByCourseId?id="+id)
				.then(function(response) {
					$scope.courseMembers = response.data;
					console.log($scope.courseMembers);
				});
			}
			
			$scope.getCourseDetailes = function(event,id){	
				courseId = id;
					$location.path("/coursesId");
			}
			
			$http.get("/coursemanagment/rest/courseSubjectService/getAllCourseSubject")
			.then(function(response) {
				$scope.allsubject = response.data;
			},function(resonse){
				$scope.error = response.statusText;
				alert("an error occured" + $scope.error);
				console.log($scope.error);
			});
			
			 myService.getAllCourseSubject().then(function(response){
					$scope.allsubject = response.data; 
				 });
			 myService.ActiveCourse().then(function(response){
					$scope.allCourses = response.data; 
				 });
			 myService.getAllLecturers().then(function(response){
					$scope.allLecturers = response.data; 
				 });
			 myService.getAllCourseSubject().then(function(response){
					$scope.allCourseSubject = response.data; 
				 });
				
			 myService.getAllRooms().then(function(response){
					$scope.allRooms = response.data; 
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
							},function(resonse){
								$scope.error = response.statusText;
								alert("an error occured" + $scope.error);
								console.log($scope.error);
							});
						}
					});
					}
			 }
			 
			 $scope.edit = function(x) {
				 $scope.courseid = $scope.allCourses[x].id;
				 $scope.upcourseName = $scope.allCourses[x].courseName;				 
				 $scope.uplecture =$scope.allCourses[x].lecturer.firstName;
				 $scope.upcourseSubject=$scope.allCourses[x].courseSubject.subject;
				 $scope.uproom = $scope.allCourses[x].rooms.roomNumber;
				 $scope.updescription = $scope.allCourses[x].description;
				 $scope.upisActive = $scope.allCourses[x].active;
				 $(".upstartdate").val($scope.allCourses[x].startDate);
				 $(".upenddate").val($scope.allCourses[x].endDate);
			 }
				
			 $scope.updateCourse = function(x) {
				 $scope.upstartDate = $(".upstartdate").val();
				 $scope.upenDate = $(".upenddate").val();
				 var todaydate = new Date();
				 var v = todaydate.getDate();
				 var k = todaydate.getMonth();
				 var l = todaydate.getFullYear();
				 var today = l+"-"+k+"-"+v;
				 console.log(today);
				 if(!( $scope.upcourseName==undefined||$scope.uplecture==undefined||$scope.upcourseSubject==undefined||$scope.uproom==undefined||
					$scope.updescription==undefined||$scope.upisActive==undefined||$scope.upstartDate==undefined||$scope.upenDate==undefined))
				 {
					 if($scope.upstartDate > today && $scope.upstartDate < $scope.upenDate){
					 $http.get("http://localhost/coursemanagment/rest/coursesService/updateCorses?id="+$scope.courseid+"&courseName="+$scope.upcourseName
							 +"&lecturer="+$scope.uplecture+"&startdate="+$scope.upstartDate+"&enddate="+$scope.upenDate+"&description="+$scope.updescription
							 +"&courseSubject="+$scope.upcourseSubject+"&room="+$scope.uproom+"&isActive="+$scope.upisActive+"&presentation=null")
						.then(function(response) {
							$scope.updateResponse = response.data;
							
						if($scope.updateResponse=="OK")
						{
							$http.get("http://localhost/coursemanagment/rest/coursesService/ActiveCourse")
							.then(function(response)
							{
								$scope.allCourses = response.data;
							},function(resonse){
								$scope.error = response.statusText;
								alert("an error occured" + $scope.error);
								console.log($scope.error);
							});
							$scope.resrartCourse();
						}
						});
					 }else{
						 alert("Start date must be greater than today and end date");
					}
				 }else{
					 alert("All fields must be complete to update a course");
				 }
			 }
			 
			 
			 
			 $scope.resrartCourse = function() {
				 $scope.upcourseName=undefined;
				 $scope.uplecture=undefined;
				 $scope.upcourseSubject=undefined;
				 $scope.uproom=undefined;
				 $scope.updescription=undefined;
				 $scope.upisActive=undefined;
				 $(".upstartdate").val(undefined);
				 $(".upenddate").val(undefined);
				 $scope.addcourseName=undefined;
				 $scope.addlecture=undefined;
				 $scope.addcourseSubject=undefined;
				 $scope.addroom=undefined;
				 $scope.adddescription=undefined;
				 $scope.addisActive=undefined;
				 $(".addstartdate").val(undefined);
				 $(".addenddate").val(undefined);
				 
			 }
	
			 $scope.addCourse = function() {
				 $scope.addstartDate = $(".addstartdate").val();
				 $scope.addenDate = $(".addenddate").val();
				 console.log($scope.addstartDate);
				 console.log($scope.addenDate);
				 var todaydate = new Date();
				 var v = todaydate.getDate();
				 var k = todaydate.getMonth();
				 var l = todaydate.getFullYear();
				 var today = l+"-"+k+"-"+v;
				 console.log(today);
				 if(!( $scope.addcourseName==undefined||$scope.addlecture==undefined||$scope.addcourseSubject==undefined||$scope.addroom==undefined||
							$scope.adddescription==undefined||$scope.addisActive==undefined||$scope.addstartDate==undefined||$scope.addenDate==undefined))
				 {
					if($scope.addstartDate > today && $scope.addstartDate < $scope.addenDate){	 
					 $http.get("http://localhost/coursemanagment/rest/coursesService/createNewCourse?&courseName="+$scope.addcourseName
									 +"&lecturer="+$scope.addlecture+"&startdate="+$scope.addstartDate+"&enddate="+$scope.addenDate+"&description="+$scope.adddescription
									 +"&courseSubject="+$scope.addcourseSubject+"&room="+$scope.addroom+"&isActive="+$scope.addisActive+"&presentation=null")
								.then(function(response)
								{
									$scope.addResponse = response.data;
								
									console.log($scope.addResponse);
								if($scope.addResponse != null)
								{
									
									
									$http.get("http://localhost/coursemanagment/rest/coursesService/ActiveCourse")
									.then(function(response)
								{
										$scope.allCourses = response.data;
										
										$scope.resrartCourse();
										$("#addmyModal").modal('hide');
										/*snackbar.create("You adding was seccusfull")*/
									});
									
								}
								},function(resonse){
									$scope.error = response.statusText;
									alert("an error occured" + $scope.error);
									console.log($scope.error);
								});
							}else{
								 alert("Start date must be greater than today and end date");
							}
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
								
							}
						},function(resonse){
							$scope.error = response.statusText;
							alert("an error occured" + $scope.error);
							console.log($scope.error);
						});
					  
					  
				  }
		
		});
		
		app.controller('courseCtrl' , function($scope,$http,$location,$rootScope,$anchorScroll,myService){
			if(userType == "student"){
				$(".daysupdateclass").hide();
					$scope.userType = userType;
					$(".second").hide();
					}
			if(userType == "admin"){
				$(".errormodal").hide();
				$(".first").hide();
				$(".formanager").show();
				$scope.userType = userType;
			}
			if(userType == "lectur"){
				$(".daysupdateclass").hide();
					$scope.userType = userType;
					$(".second").hide();
					$(".errormodal").hide();
					}
			
			$(".sideBar").hide();
			
			$rootScope.courseDetailesBtn = true;
			$rootScope.allSubjectBtnHide = true;
			
			$scope.genral = function(){
				$('html').animate({
					scrollTop: $("#General").offset().top
				},1000);
			}
			$scope.Syllabus = function(){
				$('html').animate({
					scrollTop: $("#Syllabus").offset().top
				},1000);
			}
			$scope.Schedule = function(){
				
				$('html').animate({
					scrollTop: $("#Schedule").offset().top
				},1000);
				
			}
			$scope.MassageBoard = function(){
				$('html').animate({
					scrollTop: $("#MassageBoard").offset().top
				},1000);
			}
			$scope.Presentations = function(){
				$('html').animate({
					scrollTop: $("#Presentations").offset().top
				},1000);
			}
			$scope.Resources = function(){
				$('html').animate({
					scrollTop: $("#resources").offset().top
				},1000);
				
			}
			
			$scope.ScrollToTop = function(){
				$('html').animate({
					scrollTop: $("#General").offset().top
				},1000);
			}
			myService.ActiveCourse().then(function(response){
				$scope.allCourses = response.data;
			})
			
			$scope.createday = function (){
				$scope.adddate = $(".adddate").val();
				$scope.addstartTime = $(".addstartTime").val();
				$scope.addendTime = $(".addendTime").val();
				 if(!( $scope.adddate==undefined||$scope.addstartTime==undefined||
						 $scope.addendTime==undefined||$scope.addagenda==undefined||$scope.addcourse==undefined)){
				$http.get("/coursemanagment/rest/daysService/createNewDaysToCourses?date="+$scope.adddate+
						"&agenda="+$scope.addagenda+"&startTime="+$scope.addstartTime+"&endTime="+$scope.addendTime+"&course="+$scope.addcourse)
				.then(function(response){
					$scope.createNewday = response.data;
					console.log($scope.createNewday);
				});
			}else{
				 alert("All fields must be complete to creat a new day and Schedule");
			}
				 }
			
			$scope.returnBackToUserPage = function (){
				
				$(".sideBar").show();
				$rootScope.courseDetailesBtn = false;
				$rootScope.allSubjectBtnHide = false;
				
				
				if(userType == 'student'){
					$location.path("/studentPage");
				}
				if(userType == 'admin'){
					$location.path("/managerPage");
				}
				if(userType == 'lectur'){
					$location.path("/lectur");
				}
			}

			 myService.getCoursesById(courseId).then(function(response){
					$scope.courseDetailes = response.data; 
					console.log($scope.courseDetailes);
					$scope.pptx=$scope.courseDetailes.id+"/"+$scope.courseDetailes.presentation+".pptx";
					$scope.pdf=$scope.courseDetailes.id+"/"+$scope.courseDetailes.presentation+".pdf";
				 });
			
			 myService.getDaysAssociateToCourseById(courseId).then(function(response){				 
				 $scope.courseDays = response.data;
				 console.log($scope.courseDays);
			 	});
		 
			 myService.getAllMassages(courseId).then(function(response){				 
				 $scope.allMassages = response.data;
				 console.log($scope.allMassages);
			 	});
			
			
			 
			 
			$scope.sendMassage = function(){
				
				if(typeof $scope.massageText !== 'undefined' && $scope.massageText === ""){
					alert(" must insert some")
				}else{
					
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
							$scope.massageText = "";
							alert('massage sent !');
							
							$http.get("http://localhost/coursemanagment/rest/chatService/getAllMassages?id="+courseId)
							.then(function(response) {
								$scope.allMassages = response.data;
							});
						}
			});
			
				}
		}
			
			$scope.register = function(x){
				$(".modal").show();
				$scope.dayId=$scope.courseDays[x].id;
				$(".updatethedate").val($scope.courseDays[x].date);
				$scope.updateagenda = $scope.courseDays[x].agenda;
				$(".updatestartTime").val($scope.courseDays[x].startTime);
				$(".updateendTime").val($scope.courseDays[x].endTime);
				
				console.log($scope.courseDays[x].id);
				console.log($scope.updateagenda);
				console.log($(".updatestartTime").val());
				console.log($(".updateendTime").val());
				console.log($(".updatethedate").val());
			}
			
			$scope.updateDays = function(){
				$http.get("http://localhost/coursemanagment/rest/daysService/updateDaysToCourse?id="+$scope.dayId
						+"&date="+$(".updatethedate").val()+"&agenda="+$scope.updateagenda
						+"&startTime="+$(".updatestartTime").val()+"&endTime="+$(".updateendTime").val()+"&course="+courseId)
						.then(function(response) {
							$scope.updatedDay = response.data;	
							console.log($scope.updatedDay);
							if($scope.updatedDay == "OK"){
								myService.getDaysAssociateToCourseById(courseId).then(function(response){				 
									 $scope.courseDays = response.data;
									 console.log($scope.courseDays);
									 alert("update date & schedual was successfully.");
									 
								 	});
								
								$("#modal").modal('hide');
							}else{
								alert("Operation failed");
							}
						});
			}
			
			$scope.deleteDays = function(x){
				if(confirm("Are you sure ?")==true){
				$http.get("http://localhost/coursemanagment/rest/daysService/deleteDaysToCourses?id="+$scope.courseDays[x].id)
				.then(function(response) {
				$scope.deleteDay = response.data;	
				console.log($scope.deleteDay);
				if($scope.deleteDay != null){
					myService.getDaysAssociateToCourseById(courseId).then(function(response){				 
						 $scope.courseDays = response.data;
						 console.log($scope.courseDays);
					 	});
				}else{
					alert("Operation failed");
				}
				});
				}
			}
			
			$scope.go = function(){
			$http.get("http://localhost/coursemanagment/rest/CourseMembers/createNewCourseMembers?student="+userId+"&course="+courseId)
			.then(function(response) {
			$scope.newCourseMember = response.data;	
			console.log($scope.newCourseMember);
			if($scope.newCourseMember.student != undefined){			
				alert("you been Successfully registered");
				$location.path("/coursesId");
				$(".modal").hide();
			}else{
				alert("Registration failed");
			}
			});
			}
			
			$scope.no = function(){
				$location.path("/coursesId");
				$(".modal").hide();
			}
		})
		
		
		app.controller('studentCtrl', function(myService,$scope, $http,$location,$rootScope) {
			
			$scope.showAssociateCourses = false;
			$scope.showAllCourses = true;
			$rootScope.hideAllCourses = $scope.hideAllCourses; 
			
			myService.ActiveCourse().then(function(response){
				$scope.allCourses = response.data;
			});
			
				$("#newSubject").hide();
				$("#newSubject1").hide();
				
				$scope.getCourseDetailes = function(id){
					$rootScope.allSubjectBtn = false;
					$scope.showAllCourses = false;
					courseId = id;
					$location.path("/coursesId");
				}
		});
		
		app.controller('managerCtrl', function($scope, $http,$location,$rootScope,myService) {
			
			
			
			   // wait for the DOM to be loaded 
	        $(document).ready(function() { 
	            // bind 'myForm' and provide a simple callback function 
	            $('#somform').ajaxForm(function() { 
	                alert("Upload successful"); 
	            }); 
	        }); 
			
			$scope.showAllCourses = true;
			$scope.showAssociateCourses = false;
			
			$rootScope.showAssociateCourses = $scope.showAssociateCourses;
			$rootScope.showAllCourses = $scope.showAllCourses ;
			
			
			
			$scope.getCourseDetailesOfStudents = function(id){
				$http.get("/coursemanagment/rest/CourseMembers/getCourseMembersByCourseId?id="+id)
				.then(function(response) {
					$scope.courseMembers = response.data;
					console.log($scope.courseMembers);
				});
			}
			
			$scope.getCourseDetailes = function(event,id){	
				courseId = id;
					$location.path("/coursesId");
			}
			
			$http.get("/coursemanagment/rest/courseSubjectService/getAllCourseSubject")
			.then(function(response) {
				$scope.allsubject = response.data;
			},function(resonse){
				$scope.error = response.statusText;
				alert("an error occured" + $scope.error);
				console.log($scope.error);
			});
			
			 myService.getAllCourseSubject().then(function(response){
					$scope.allsubject = response.data; 
				 });
			 myService.ActiveCourse().then(function(response){
					$scope.allCourses = response.data; 
				 });
			 myService.getAllLecturers().then(function(response){
					$scope.allLecturers = response.data; 
				 });
			 myService.getAllCourseSubject().then(function(response){
					$scope.allCourseSubject = response.data; 
				 });
				
			 myService.getAllRooms().then(function(response){
					$scope.allRooms = response.data; 
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
							},function(resonse){
								$scope.error = response.statusText;
								alert("an error occured" + $scope.error);
								console.log($scope.error);
							});
						}
					});
					}
			 }
			 
			 $scope.edit = function(x) {
				 $scope.courseid = $scope.allCourses[x].id;
				 $scope.upcourseName = $scope.allCourses[x].courseName;				 
				 $scope.uplecture =$scope.allCourses[x].lecturer.firstName;
				 $scope.upcourseSubject=$scope.allCourses[x].courseSubject.subject;
				 $scope.uproom = $scope.allCourses[x].rooms.roomNumber;
				 $scope.updescription = $scope.allCourses[x].description;
				 $scope.upisActive = $scope.allCourses[x].active;
				 $(".upstartdate").val($scope.allCourses[x].startDate);
				 $(".upenddate").val($scope.allCourses[x].endDate);
			 }
				
			 $scope.updateCourse = function(x) {
				 $scope.upstartDate = $(".upstartdate").val();
				 $scope.upenDate = $(".upenddate").val();
				 var todaydate = new Date();
				 var v = todaydate.getDate();
				 var k = todaydate.getMonth();
				 var l = todaydate.getFullYear();
				 var today = l+"-"+k+"-"+v;
				 console.log(today);
				 if(!( $scope.upcourseName==undefined||$scope.uplecture==undefined||$scope.upcourseSubject==undefined||$scope.uproom==undefined||
					$scope.updescription==undefined||$scope.upisActive==undefined||$scope.upstartDate==undefined||$scope.upenDate==undefined))
				 {
					 if($scope.upstartDate > today && $scope.upstartDate < $scope.upenDate){
					 $http.get("http://localhost/coursemanagment/rest/coursesService/updateCorses?id="+$scope.courseid+"&courseName="+$scope.upcourseName
							 +"&lecturer="+$scope.uplecture+"&startdate="+$scope.upstartDate+"&enddate="+$scope.upenDate+"&description="+$scope.updescription
							 +"&courseSubject="+$scope.upcourseSubject+"&room="+$scope.uproom+"&isActive="+$scope.upisActive+"&presentation=null")
						.then(function(response) {
							$scope.updateResponse = response.data;
							
						if($scope.updateResponse=="OK")
						{
							$http.get("http://localhost/coursemanagment/rest/coursesService/ActiveCourse")
							.then(function(response)
							{
								$scope.allCourses = response.data;
							},function(resonse){
								$scope.error = response.statusText;
								alert("an error occured" + $scope.error);
								console.log($scope.error);
							});
							$scope.resrartCourse();
						}
						});
					 }else{
						 alert("Start date must be greater than today and end date");
					}
				 }else{
					 alert("All fields must be complete to update a course");
				 }
			 }
			 
			 
			 
			 $scope.resrartCourse = function() {
				 $scope.upcourseName=undefined;
				 $scope.uplecture=undefined;
				 $scope.upcourseSubject=undefined;
				 $scope.uproom=undefined;
				 $scope.updescription=undefined;
				 $scope.upisActive=undefined;
				 $(".upstartdate").val(undefined);
				 $(".upenddate").val(undefined);
				 $scope.addcourseName=undefined;
				 $scope.addlecture=undefined;
				 $scope.addcourseSubject=undefined;
				 $scope.addroom=undefined;
				 $scope.adddescription=undefined;
				 $scope.addisActive=undefined;
				 $(".addstartdate").val(undefined);
				 $(".addenddate").val(undefined);
				 
			 }
	
			 $scope.addCourse = function() {
				 $scope.addstartDate = $(".addstartdate").val();
				 $scope.addenDate = $(".addenddate").val();
				 console.log($scope.addstartDate);
				 console.log($scope.addenDate);
				 var todaydate = new Date();
				 var v = todaydate.getDate();
				 var k = todaydate.getMonth();
				 var l = todaydate.getFullYear();
				 var today = l+"-"+k+"-"+v;
				 console.log(today);
				 if(!( $scope.addcourseName==undefined||$scope.addlecture==undefined||$scope.addcourseSubject==undefined||$scope.addroom==undefined||
							$scope.adddescription==undefined||$scope.addisActive==undefined||$scope.addstartDate==undefined||$scope.addenDate==undefined))
				 {
					if($scope.addstartDate > today && $scope.addstartDate < $scope.addenDate){	 
					 $http.get("http://localhost/coursemanagment/rest/coursesService/createNewCourse?&courseName="+$scope.addcourseName
									 +"&lecturer="+$scope.addlecture+"&startdate="+$scope.addstartDate+"&enddate="+$scope.addenDate+"&description="+$scope.adddescription
									 +"&courseSubject="+$scope.addcourseSubject+"&room="+$scope.addroom+"&isActive="+$scope.addisActive+"&presentation=null")
								.then(function(response)
								{
									$scope.addResponse = response.data;
								
									console.log($scope.addResponse);
								if($scope.addResponse != null)
								{
									
									
									$http.get("http://localhost/coursemanagment/rest/coursesService/ActiveCourse")
									.then(function(response)
								{
										$scope.allCourses = response.data;
										
										$scope.resrartCourse();
										$("#addmyModal").modal('hide');
										/*snackbar.create("You adding was seccusfull")*/
									});
									
								}
								},function(resonse){
									$scope.error = response.statusText;
									alert("an error occured" + $scope.error);
									console.log($scope.error);
								});
							}else{
								 alert("Start date must be greater than today and end date");
							}
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
								
							}
						},function(resonse){
							$scope.error = response.statusText;
							alert("an error occured" + $scope.error);
							console.log($scope.error);
						});
					  
					  
				  }
		
		});