function compareDate(start, end) {
	if (start == null || start.length == 0 || end == null || end.length == 0) {
		return 0;
	}

	var arr = start.split("-");
	var starttime = new Date(arr[0], arr[1], arr[2]);
	var starttimes = starttime.getTime();

	var arrs = end.split("-");
	var endtime = new Date(arrs[0], arrs[1], arrs[2]);
	var endtimes = endtime.getTime();

	var intervalTime = endtimes - starttimes;// 两个日期相差的毫秒数 一天86400000毫秒
	var Inter_Days = ((intervalTime).toFixed(2) / 86400000) + 1;// 加1，是让同一天的两个日期返回一天

	return Inter_Days;
}