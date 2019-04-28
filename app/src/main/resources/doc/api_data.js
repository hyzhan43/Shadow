define({ "api": [
  {
    "type": "get",
    "url": "/admin/authority",
    "title": "获取所有可分配权限",
    "group": "admin",
    "version": "1.0.0",
    "success": {
      "examples": [
        {
          "title": "返回样例：",
          "content": "{\"code\":0,\"msg\":\"获取成功\",\"data\":[{\"auth\":\"查询所有用户\",\"module\":\"管理员\",\"mount\":false},{\"auth\":\"删除用户\",\"module\":\"管理员\",\"mount\":false},{\"auth\":\"修改用户密码\",\"module\":\"管理员\",\"mount\":false}]}",
          "type": "json"
        }
      ]
    },
    "filename": "./app/src/main/java/com/example/app/controller/cms/AdminController.java",
    "groupTitle": "admin",
    "name": "GetAdminAuthority"
  },
  {
    "type": "get",
    "url": "/admin/users",
    "title": "获取所有用户",
    "group": "admin",
    "version": "1.0.0",
    "success": {
      "examples": [
        {
          "title": "返回样例：",
          "content": "{\"code\":0,\"msg\":\"获取成功\",\"data\":{\"curPage\":0,\"pageCount\":1,\"size\":10,\"total\":2,\"collection\":[{\"id\":1,\"nickname\":\"asd\",\"active\":1,\"email\":\"asd\",\"groupId\":1,\"groupName\":\"张三\",\"admin\":1},{\"id\":2,\"nickname\":\"ww\",\"active\":1,\"email\":\"ww\",\"groupId\":2,\"groupName\":\"aa\",\"admin\":1}]}}",
          "type": "json"
        }
      ]
    },
    "filename": "./app/src/main/java/com/example/app/controller/cms/AdminController.java",
    "groupTitle": "admin",
    "name": "GetAdminUsers"
  }
] });
