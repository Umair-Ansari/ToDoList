function addTask()
			{
				
			var task = $('#task').val();
			task = task.trim(); 
			if(task.match(/^[a-zA-Z0-9\s]{3,60}$/)) {
				$.ajax({
					type: "POST",
					url: "AddTaskPath",
					data: "description="+ task ,
					success: function(html){
						$("#list").html(html);
						$("#alert").html("");
					}
				});
			return false;
			}
			else
				$("#alert").html("<div style='background-color:#FFBABA;border:1px solid #D8000C;padding:7pt;width:400pt;margin-left: -30pt'><b>Must contain 3 to 60 letters only</b></div>");
		}
	
	function toDelete(val){
		$('#deleteTask').val(val);
		$('#deleteSingle').bPopup({
			easing: 'easeOutBack',
	    	speed: 450,
	    	transition: 'slideDown'
		});
	}
	function clearAll(){
		$('#deleteAll').bPopup({
			easing: 'easeOutBack',
	    	speed: 450,
	    	transition: 'slideDown'
		});
	}
	function taskChecked(id){
		var status = false;
		if($("#check"+id).is(':checked'))
			status = true;
		if(id != 0){
			$.ajax({
				type: "POST",
				url: "TaskCompletedPath",
				data: "status="+status+"&LId="+ id ,
				success: function(html){
					if(html == "true")
						$("#task"+id+"").toggleClass('checked');	
					else
						$("#task"+id+"").toggleClass('');
				}
			});
		}
	}