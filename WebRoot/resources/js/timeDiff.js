/**
 * 判断两个日期不能相差一个月 yyyy-mm-dd
 * @param startTime 开始日期
 * @param endTime 结束日期
 * @returns {boolean}
 */
function oneMonthTimeDiff(startTime, endTime) {
    var startDate = new Date(startTime);
    var endDate = new Date(endTime);
    if (startDate > endDate) {
        alertMsg.warn("查询开始日期不能大于结束日期");
        return false;
    }
    if (Math.abs(startDate - endDate) > 2678400000) {
        alertMsg.warn("查询日期不能超过一个月");
        return false;
    }
    return true;
}