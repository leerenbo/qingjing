<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet prefetch"
	href="<%=request.getContextPath()%>/cdn/js/antd/antd.css">

<link rel="stylesheet prefetch"
	href="<%=request.getContextPath()%>/cdn/css/index.css">

<script
	src="<%=request.getContextPath()%>/cdn/js/react-15.2.1/build/react.js"></script>
<script
	src="<%=request.getContextPath()%>/cdn/js/react-15.2.1/build/react-dom.js"></script>
<script
	src="<%=request.getContextPath()%>/cdn/js/babel-core-6.12.0/browser.min.js"></script>
<script src="<%=request.getContextPath()%>/cdn/js/antd/antd.js"></script>
<script src="<%=request.getContextPath()%>/cdn/js/FastJson-1.0.min.js"></script>
<script
	src="<%=request.getContextPath()%>/cdn/js/jquery/jquery-3.1.0.js"></script>
<script src="<%=request.getContextPath()%>/cdn/js/javascript-extends.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="page"></div>
	<script type="text/babel">
const {message,notification ,Popconfirm ,Radio,Tooltip,InputNumber ,Checkbox , Modal,Menu, Breadcrumb, Icon,Tabs,Button,Form, Input, Row, Col, DatePicker, Table } = antd;
const SubMenu = Menu.SubMenu;
const TabPane = Tabs.TabPane;
const FormItem = Form.Item;
const RadioGroup = Radio.Group;
const InputGroup = Input.Group;

var ListInput=React.createClass({
	info(){
		this.props.form.setFieldsValue({'3':'a'});
		console.info(this.props.form.getFieldsValue());
	},
	render(){
    const { getFieldProps } = this.props.form;
	console.info(this.props.form.getFieldsValue());
		 return (
<div>
			<Form horizontal className="ant-advanced-search-form" ref="queryForm">
<FormItem label="用户名">
    <InputGroup {...getFieldProps('3')} size="large" defaultValue="0571" >
      <Col span="4">
        <Input {...getFieldProps('1')} defaultValue="0571" />
      </Col>
      <Col span="8">
        <Input {...getFieldProps('2')}  defaultValue="26888888" />
      </Col>
    </InputGroup>
</FormItem>
			</Form>  
<Button onClick={this.info}>bbb</Button>
</div>

)
	}
})
ListInput=Form.create()(ListInput);

ReactDOM.render(<ListInput/>, page);


</script>
</body>
</html>