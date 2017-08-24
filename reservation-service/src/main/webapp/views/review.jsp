<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="/resources/css/style.css" rel="stylesheet">
</head>

<body>
    <div id="container">
		<!-- [D] 예약하기로 들어오면 header에 fade 클래스 추가로 숨김 -->
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="#" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
					<a href="#" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
				</h1>
				<a href="/myreservation" class="btn_my"> <span title="내 예약">MY</span> </a>
			</header>
		</div>
        <div class="ct">
            <div class="wrap_review_list">
                <div class="review_header">
                    <div class="top_title gr">
                        <a href="#" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i> </a>
                        <h2><a class="title" href="#">${product.name}</a></h2>
                    </div>
                </div>
                <div class="section_review_list">
                    <div class="review_box">
                        <h3 class="title_h3">예매자 한줄평</h3>
                        <div class="short_review_area">
                            <div class="grade_area"> <span class="graph_mask"> <em class="graph_value" style="width: 88%;"></em> </span> <strong class="text_value"> <span>4.4</span> <em class="total">5.0</em> </strong> <span class="join_count"><em class="green">20건</em> 등록</span>                                </div>
                            <ul class="list_short_review">
                                
                            </ul>
                        </div>
                        
                        <p class="guide"> <i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span> </p>
                    </div>
                </div>
            </div>
        </div>
        <hr> 
    </div>
	<footer>
        <div class="gototop">
            <a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
        </div>
        <div id="footer" class="footer">
            <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
            <span class="copyright">© NAVER Corp.</span>
        </div>
    </footer>
	    
	<div id="photoviewer" style="display:none; position:fixed; top:0">	
        <div class="group_visual" style="position:absolute;">
        	<div class="photoviewer_closer" style="position: absolute; right:0; top:0; width:40px; height:40px; color:white; z-index:10;">close</div>
	        <div class="pagination">
	            <div class="bg_pagination">
	            </div>
	            <div class="figure_pagination">
	                <span class="num">1</span>
	                <span class="num off">/ <span>3</span></span>
	            </div>
	        </div>
            <div>
                <div class="container_visual" style="width: 414px;">
                    <ul class="visual_img">
                    	
                    </ul>
                </div>
                <div class="prev">
                    <div class="prev_inn">
                        <a href="#" class="btn_prev" title="이전">
                            <!-- [D] 첫 이미지 이면 off 클래스 추가 -->
                            <i class="spr_book2 ico_arr6_lt off"></i>
                        </a>
                    </div>
                </div>
                <div class="nxt">
                    <div class="nxt_inn">
                        <a href="#" class="btn_nxt" title="다음">
                            <i class="spr_book2 ico_arr6_rt"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/handlebars" id="photoviewer-image-template">
		{{#images}}
		<li class="item" style="width: 414px;" data-id="{{id}}"> <img alt="{{fileName}}" class="img_thumb" src="{{saveFileName}}"> 
			<span class="img_bg"></span>
		   <div class="visual_txt">
		       <div class="visual_txt_inn">
		           <h2 class="visual_txt_tit"> <span>${product.name}</span> </h2>
		            <p class="visual_txt_dsc"></p>
		        </div>
		    </div>
		</li>
		{{/images}}
	</script>
   	<script type="text/handlebars" id="comment-template">
	    {{#comment}}
		<li class="list_item" data-id={{id}} >
			<div>
				<div class="review_area">
					<div class="thumb_area {{displayOption}}" >
					<a href="#" class="thumb" title="이미지 크게 보기"> <img width="90" height="90" class="img_vertical_top" src="{{firstImageSaveFileName}}" alt="리뷰이미지" > </a> 
					<span class="img_count">{{imageCount}}</span>                                                
				</div>
				<h4 class="resoc_name">{{productName}}</h4>
				<p class="review">{{comment}}</p>
			</div>
			<div class="info_area">
				<div class="review_info"> <span class="grade">{{score}}</span> <span class="name">dbfl****</span> <span class="date">{{modifyDate}}	 방문</span> </div>
				</div>
			</div>
		</li>
		{{/comment}}
	</script>
	<script>
    	function getProductByEl() {
    		return JSON.parse('${product}');
    	}
    </script>
    <script data-main="/resources/js/review/review" src="/resources/node_modules/requirejs/require.js"></script>
</body>

</html>
