queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from core_role t
    where 1=1  
    @//数据权限，该sql语句功能点,如果不考虑数据权限，可以删除此行  
    and #function("coreRole.query")#
