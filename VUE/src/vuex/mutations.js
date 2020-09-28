//对仓库属性的修改与定义
export const changeLoginType = (state,type) =>{
    state.loginType = type;
};
//修改登陆用户名，并存储到仓库
export const changeLogName = (state,name) => {
    state.logName = name;
};
//修改用户
export const changeId = (state,userId)=>{
    state.userId = userId;
};
//设置用户类型
export const changeType = (state,userType)=>{
    state.userType = userType;
};