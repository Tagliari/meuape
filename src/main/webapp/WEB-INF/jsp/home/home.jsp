
<%-- It works!! ${variable} ${linkTo[HomeController].index} --%>
<template:insert template='/includes/defaultTemplate.jsp'>

	<template:put name="metatags" direct="true">
	</template:put>

	<template:put name="scriptsHeader" direct="true">
	</template:put>
	
	<template:put name="header" direct="true">

		<div id="preloader">
			<div id="status"></div>
		</div>
		
		<jsp:include page="/components/header_default.jsp"/>
	
	</template:put>
	
	<template:put name="content" direct="true">

		<div id="whatis" class="content-section-b" style="border-top: 0">
			<div class="container">
	
				<div class="col-md-6 col-md-offset-3 text-center wrap_title">
					<h2><fmt:message key="navigation.whatis"/></h2>
					<p class="lead" style="margin-top:0"><fmt:message key="home.phrase1"/></p>
				</div>
				
				
				<div class="row tworow">
				
					<div class="col-sm-4  wow fadeInDown text-center">
					  <img class="rotate" src="${staticUrl}/static/images/icon/laptop.svg" alt="Generic placeholder image">
					  <h3><fmt:message key="home.section.whatis.myaccount"/></h3>
					  <p class="lead"><fmt:message key="home.section.whatis.myaccount.phrase"/></p>
<!-- 					 <p><a class="btn btn-embossed btn-primary view" role="button">View Details</a></p> -->
					</div><!-- /.col-lg-4 -->
					
					<div class="col-sm-4 wow fadeInDown text-center">
					  <img  class="rotate" src="${staticUrl}/static/images/icon/map.svg" alt="Generic placeholder image">
					   <h3><fmt:message key="home.section.whatis.opinions"/></h3>
					   <p class="lead"><fmt:message key="home.section.whatis.opinions.phrase"/></p>
					   <!-- <p><a class="btn btn-embossed btn-primary view" role="button">View Details</a></p> -->
					</div><!-- /.col-lg-4 -->
					
					<div class="col-sm-4 wow fadeInDown text-center">
					  <img  class="rotate" src="${staticUrl}/static/images/icon/browser.svg" alt="Generic placeholder image">
					   <h3><fmt:message key="home.section.whatis.message"/></h3>
					 <p class="lead"><fmt:message key="home.section.whatis.message.phrase"/></p>
					  <!-- <p><a class="btn btn-embossed btn-primary view" role="button">View Details</a></p> -->
					</div><!-- /.col-lg-4 -->
					
				</div>
				
				<div class="row">
					<%--
					<div class="col-sm-4 wow fadeInDown text-center">
					  <img class="rotate" src="${staticUrl}/static/images/icon/tweet.svg" alt="Generic placeholder image">
					  <h3>Follow Me</h3>
					  <p class="lead">Epsum factorial non deposit quid pro quo hic escorol. Olypian quarrels et gorilla congolium sic ad nauseum. </p>
					  <!-- <p><a class="btn btn-embossed btn-primary view" role="button">View Details</a></p> -->
					</div><!-- /.col-lg-4 -->
					--%>
					<div class="col-sm-6 wow fadeInDown text-center">
					  <img  class="rotate" src="${staticUrl}/static/images/icon/picture.svg" alt="Generic placeholder image">
					   <h3><fmt:message key="home.section.whatis.gallery"/></h3>
					   <p class="lead"><fmt:message key="home.section.whatis.gallery.phrase"/></p>
					   <!-- <p><a class="btn btn-embossed btn-primary view" role="button">View Details</a></p> -->
					</div><!-- /.col-lg-4 -->
					
					<div class="col-sm-6 wow fadeInDown text-center">
					  <img  class="rotate" src="${staticUrl}/static/images/icon/retina.svg" alt="Generic placeholder image">
					   <h3><fmt:message key="home.section.whatis.payment"/></h3>
						<p class="lead"><fmt:message key="home.section.whatis.payment.phrase"/></p>
					  <!-- <p><a class="btn btn-embossed btn-primary view" role="button">View Details</a></p> -->
					</div><!-- /.col-lg-4 -->
					
				</div>
					
				
			</div>
		</div>
		
		<!-- Use it -->
	    <div id ="useit" class="content-section-a">
	
	        <div class="container">
				
	            <div class="row">
				
					<div class="col-sm-6 pull-right wow fadeInRightBig">
	                    <img class="img-responsive " src="${staticUrl}/static/images/ipad.png" alt="">
	                </div>
					
	                <div class="col-sm-6 wow fadeInLeftBig"  data-animation-delay="200">   
	                    <h3 class="section-heading"><fmt:message key="home.section.myaccount"/></h3>
						<div class="sub-title lead3"><fmt:message key="home.section.myaccount.title"/></div>
	                    <p class="lead"><fmt:message key="home.section.myaccount.subtitle"/></p>
	
						 <%--<p><a class="btn btn-embossed btn-primary" href="#" role="button">View Details</a> 
						 <a class="btn btn-embossed btn-info" href="#" role="button">Visit Website</a></p>
						  --%>
					</div>   
	            </div>
	        </div>
	        <!-- /.container -->
	    </div>
	
	    <div id="screen" class="content-section-b"> 
			
			<div class="container">
	            <div class="row">
	                <div class="col-sm-6 wow fadeInLeftBig">
	                     <div id="owl-demo-1" class="owl-carousel">
							<a href="${staticUrl}/static/images/iphone.png" class="image-link">
								<div class="item">
									<img  class="img-responsive img-rounded" src="${staticUrl}/static/images/iphone.png" alt="">
								</div>
							</a>
<%-- 							<a href="${staticUrl}/static/images/iphone.png" class="image-link"> --%>
<!-- 								<div class="item"> -->
<%-- 									<img  class="img-responsive img-rounded" src="${staticUrl}/static/images/iphone.png" alt=""> --%>
<!-- 								</div> -->
<!-- 							</a> -->
<%-- 							<a href="${staticUrl}/static/images/iphone.png" class="image-link"> --%>
<!-- 								<div class="item"> -->
<%-- 									<img  class="img-responsive img-rounded" src="${staticUrl}/static/images/iphone.png" alt=""> --%>
<!-- 								</div> -->
<!-- 							</a> -->
						</div>       
	                </div>
					
	                <div class="col-sm-6 wow fadeInRightBig"  data-animation-delay="200">   
	                    <h3 class="section-heading"><fmt:message key="home.section.gallery"/></h3>
						<div class="sub-title lead3"><fmt:message key="home.section.gallery.title"/></div>
	                    <p class="lead">
							<fmt:message key="home.section.gallery.subtitle"/>
						</p>
	
						 <%--<p><a class="btn btn-embossed btn-primary" href="#" role="button">View Details</a> 
						 <a class="btn btn-embossed btn-info" href="#" role="button">Visit Website</a></p>
						  --%>
					</div> 
					
					
<!-- 					<div class="row" > -->
<!-- 						<div class="col-md-6 col-md-offset-3 text-center wrap_title "> -->
<!-- 							<h2>Screen App</h2> -->
<!-- 							<p class="lead" style="margin-top:0">A special thanks to Death.</p> -->
<!-- 						</div> -->
<!-- 					</div> -->
					<div class="row wow bounceInUp" >
						<div id="owl-demo" class="owl-carousel">
							<a href="${staticUrl}/static/images/slide/1.png" class="image-link">
								<div class="item">
									<img  class="img-responsive img-rounded" src="${staticUrl}/static/images/slide/1.png" alt="Owl Image">
								</div>
							</a>
				            <a href="${staticUrl}/static/images/slide/2.png" class="image-link">
							<div class="item">
								<img  class="img-responsive img-rounded" src="${staticUrl}/static/images/slide/2.png" alt="Owl Image">
							</div>
							</a>
							
							<a href="${staticUrl}/static/images/slide/3.png" class="image-link">
								<div class="item">
									<img  class="img-responsive img-rounded" src="${staticUrl}/static/images/slide/3.png" alt="Owl Image">
								</div>
							</a>
							
							<a href="${staticUrl}/static/images/slide/1.png" class="image-link">
								<div class="item">
									<img  class="img-responsive img-rounded" src="${staticUrl}/static/images/slide/1.png" alt="Owl Image">
								</div>
							</a>
							
				           <a href="${staticUrl}/static/images/slide/2.png" class="image-link">
								<div class="item">
									<img  class="img-responsive img-rounded" src="${staticUrl}/static/images/slide/2.png" alt="Owl Image">
								</div>
							</a>
							
							<a href="${staticUrl}/static/images/slide/3.png" class="image-link">
								<div class="item">
									<img  class="img-responsive img-rounded" src="${staticUrl}/static/images/slide/3.png" alt="Owl Image">
								</div>
							</a>
						</div>       
					</div>
					 			
				</div>
			</div>
		</div>
	    <%--
	    <div class="content-section-a">
	        <div class="container">
	             <div class="row">
				 
					<div class="col-sm-6 pull-right wow fadeInRightBig">
	                    <img class="img-responsive " src="${staticUrl}/static/images/doge.png" alt="">
	                </div>
				 
	                <div class="col-sm-6 wow fadeInLeftBig"  data-animation-delay="200">   
	                    <h3 class="section-heading"><fmt:message key="home.section.credits.title"/></h3>
	                    <p class="lead">
						</p>
						<ul class="descp lead2">
							<li><i class="glyphicon glyphicon-signal"></i><fmt:message key="home.section.credits.developer.name"/></li>
							 <%--<li><i class="glyphicon glyphicon-refresh"></i> Everything is perfectly orgainized for future</li>
							<li><i class="glyphicon glyphicon-headphones"></i> Attach large file easily</li> 
						</ul>
					</div>           
	            </div>
	        </div>
	
	    </div> --%>
	
<%--
		<div  class="content-section-c ">
			<div class="container">
				<div class="row">
				<div class="col-md-6 col-md-offset-3 text-center white">
					<h2>Get Live Updates</h2>
					<p class="lead" style="margin-top:0">A special thanks to Death.</p>
				 </div>
				<div class="col-md-6 col-md-offset-3 text-center">
					<div class="mockup-content">
							<div class="morph-button wow pulse morph-button-inflow morph-button-inflow-1">
								<button type="button "><span>Subscribe to our Newsletter</span></button>
								<div class="morph-content">
									<div>
										<div class="content-style-form content-style-form-4 ">
											<h2 class="morph-clone">Subscribe to our Newsletter</h2>
											<form>
												<p><label>Your Email Address</label><input type="text"/></p>
												<p><button>Subscribe me</button></p>
											</form>
										</div>
									</div>
								</div>
							</div>
					</div>
				</div>	
				</div>
			</div>
		</div>  --%>
		
		<div id="credits" class="content-section-a">
			<div class="container">
				<div class="row">
				
					<div class="col-md-6 col-md-offset-3 text-center wrap_title">
						<h2><fmt:message key="home.section.credits.title"/></h2>
						<%--<p class="lead" style="margin-top:0">A special thanks to Death.</p> --%>
					 </div>
					 
					<div class="col-sm-6  block wow bounceIn">
						<div class="row">
							<div class="col-md-4 box-icon rotate"> 
								<i class="fa fa-desktop fa-4x "> </i> 
							</div>
							<div class="col-md-8 box-ct">
								<h3><fmt:message key="home.section.credits.developer.name"/></h3>
								<p>Desenvolvedor de software (Especialista Oracle Commerce) </p>
							</div>
						</div>
					</div>
				  <div class="col-sm-6 block wow bounceIn">
						<div class="row">
						  <div class="col-md-4 box-icon rotate"> 
							<i class="fa fa-picture-o fa-4x "> </i> 
						  </div>
						  <div class="col-md-8 box-ct">
							<h3> Contato </h3>
							<p> Linkedin.</p>
							<p> Facebook.</p> 
						  </div>
						</div>
				  </div> 
			  </div>

			 <%-- <div class="row tworow">
					<div class="col-sm-6  block wow bounceIn">
						<div class="row">
							<div class="col-md-4 box-icon rotate"> 
								<i class="fa fa-magic fa-4x "> </i> 
							</div>
							<div class="col-md-8 box-ct">
								<h3> Codrops </h3>
								<p> Lorem ipsum dolor sit ametconsectetur adipiscing elit. Suspendisse orci quam. </p>
							</div>
					  </div>
				  </div>
				  <div class="col-sm-6 block wow bounceIn">
						<div class="row">
						  <div class="col-md-4 box-icon rotate"> 
							<i class="fa fa-heart fa-4x "> </i> 
						  </div>
						  <div class="col-md-8 box-ct">
							<h3> Lorem Ipsum</h3>
							<p> Nullam mo  arcu ac molestie scelerisqu vulputate, molestie ligula gravida, tempus ipsum.</p> 
						  </div>
						</div>
				  </div>
			  </div> --%>
			</div>
		</div>
		
		<%--<div id="downloadlink" class="banner">
			<div class="container">
				<div class="row">
					<div class="col-md-6 col-md-offset-3 text-center wrap_title">
					<h2>Download Free</h2>
					<p class="lead" style="margin-top:0">Pay with a Tweet</p>
					<p><a class="btn btn-embossed btn-primary view" role="button">Free Download</a></p> 
				 </div>
				</div>
			</div>
		</div>
		 --%>
		
		<%-- Syndic 
		<div id="syndic" class="content-section-a">
			<div class="container">
				<div class="row">
				
				<div class="col-md-6 col-md-offset-3 text-center wrap_title">
					<h2>Contact Us</h2>
				</div>
				
				<form role="form" action="" method="post" >
					<div class="col-md-6">
						<div class="form-group">
							<label for="InputName">Your Name</label>
							<div class="input-group">
								<input type="text" class="form-control" name="InputName" id="InputName" placeholder="Enter Name" required>
								<span class="input-group-addon"><i class="glyphicon glyphicon-ok form-control-feedback"></i></span>
							</div>
						</div>
						
						<div class="form-group">
							<label for="InputEmail">Your Email</label>
							<div class="input-group">
								<input type="email" class="form-control" id="InputEmail" name="InputEmail" placeholder="Enter Email" required  >
								<span class="input-group-addon"><i class="glyphicon glyphicon-ok form-control-feedback"></i></span>
							</div>
						</div>
						
						<div class="form-group">
							<label for="InputMessage">Message</label>
							<div class="input-group">
								<textarea name="InputMessage" id="InputMessage" class="form-control" rows="5" required></textarea>
								<span class="input-group-addon"><i class="glyphicon glyphicon-ok form-control-feedback"></i></span>
							</div>
						</div>
	
						<input type="submit" name="submit" id="submit" value="Submit" class="btn wow tada btn-embossed btn-primary pull-right">
					</div>
				</form>
				
				<hr class="featurette-divider hidden-lg">
					<div class="col-md-5 col-md-push-1 address">
						<address>
						<h3>Office Location</h3>
						<p class="lead"><a href="https://www.google.com/maps/preview?ie=UTF-8&q=The+Pentagon&fb=1&gl=us&hq=1400+Defense+Pentagon+Washington,+DC+20301-1400&cid=12647181945379443503&ei=qmYfU4H8LoL2oATa0IHIBg&ved=0CKwBEPwSMAo&safe=on">The Pentagon<br>
						Washington, DC 20301</a><br>
						Phone: XXX-XXX-XXXX<br>
						Fax: XXX-XXX-YYYY</p>
						</address>
	
						<h3>Social</h3>
						<li class="social"> 
						<a href="#"><i class="fa fa-facebook-square fa-size"> </i></a>
						<a href="#"><i class="fa  fa-twitter-square fa-size"> </i> </a> 
						<a href="#"><i class="fa fa-google-plus-square fa-size"> </i></a>
						<a href="#"><i class="fa fa-flickr fa-size"> </i> </a>
						</li>
					</div>
				</div>
			</div>
		</div>--%>
		
    
	</template:put>

</template:insert>