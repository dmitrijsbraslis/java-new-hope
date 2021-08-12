getItems("http://localhost:8090/getItems");

function getItems(url) {
	$.get(url, function(data) {
		var result = "";
		
		$.each(data, function(index, item) {
			result += "<div class=\"catalog-item\">";
			result += "<div>";
				result += "<div class=\"catalog-links\">";
					result += "<div class=\"category\">" + item.subcategory.category.name + "</div>";
					result += "<div class=\"category-delim\"> >> </div>";
					result += "<div class=\"category\" onClick=\"getItems('http://localhost:8090/getItems/" + item.subcategory.id + "');\">" + item.subcategory.name + "</div>";
				result += "</div>";
				result += "<div><img src=\"noimage.png\" class=\"catalog-img\"></div>";
				result += "<div class=\"catalog-name\">" + item.name + "</div>";
				result += "<div class=\"catalog-descr\">" + item.description + "</div>"
			result += "</div>";
			result += "<div>";
				result += "<div class=\"catalog-price\">" + item.price + " €</div>";
				result += "<div class=\"catalog-btn\">ADD TO CART</div>";
			result += "</div></div>";
		});
		
		$("#catalog").html(result);
	});
}