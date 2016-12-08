<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet prefetch" href="<%=request.getContextPath()%>/cdn/js/antd/antd.css">

<link rel="stylesheet prefetch" href="<%=request.getContextPath()%>/cdn/css/index.css">

<script src="<%=request.getContextPath()%>/cdn/js/react-15.2.1/build/react.js"></script>
<script src="<%=request.getContextPath()%>/cdn/js/react-15.2.1/build/react-dom.js"></script>
<script src="<%=request.getContextPath()%>/cdn/js/babel-core-6.12.0/browser.min.js"></script>
<script src="<%=request.getContextPath()%>/cdn/js/antd/antd.js"></script>
<script src="<%=request.getContextPath()%>/cdn/js/FastJson-1.0.min.js"></script>
<script src="<%=request.getContextPath()%>/cdn/js/jquery/jquery-3.1.0.js"></script>
<script src="<%=request.getContextPath()%>/cdn/js/javascript-extends.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="page"></div>
	<script type="text/babel">
const {message,notification ,Popconfirm ,Radio,Tooltip,InputNumber ,Checkbox , Modal,Menu, Breadcrumb, Icon,Tabs,Button,Form, Input, Row, Col, DatePicker, Table ,Select} = antd;
const SubMenu = Menu.SubMenu;
const TabPane = Tabs.TabPane;
const FormItem = Form.Item;
const RadioGroup = Radio.Group;
const Option = Select.Option;

const InputList=React.createClass({
	render(){
		
	},
})

var saasUtil={
	format(form){
		for(let v in form){
			let index=v.indexOf('$');
			if(index>-1){
				let arrayName=v.substr(0,index);
				if(form[arrayName]==undefined){
					form[arrayName]=[];
				}
				form[arrayName].push(form[v]);
			}
		}
		return form;
	},
	isFieldTypeSupport(field){
		if(field.type.indexOf('model')>-1){
			return true;
		}
		switch (field.type){
			case 'java.lang.String': 
			case 'java.lang.Integer':
			case 'java.util.Date':
			case 'java.lang.Boolean':
			case 'java.util.List<java.lang.String>':
				return true;
			default:
				console.info(field);
				return false;
		}
	},
	query(url,data,success,error){
		let request={};
		request.body=data;
		request.token='token';
		request.client='saas-manager';
		request.tenant='weixin';
		request.channel='channel';

		let re;
		$.ajax({
			'type':'POST',
			'url':url,
			'async':false,
			'contentType':'application/json',
			'data':JSON.stringify(request),
			'success':
				function(data){
					data=FastJson.format(data);
					let info={};
					switch (data.result){
						case 'success':
							re=data.body;
							break;
						case 'invalid':
							info.message=data.message;
							info.description=data.invalidInfos.join('<br/>');
							info.duration=0;
							notification.error(info);
							break;
						case 'fail':
							info.message=data.message;
							info.description=data.exception;
							info.duration=0;
							notification.error(info);
							break;
					}
					if(success){
						success(data);
					}
				},
			'error':
				function(data){
					const info={};
					info.message='调用异常';
					info.description=data;
					info.duration=0;
					notification.error(info);
					if(error){
						error(data);
					}
				}
		});
		return re;
	},
	post(url,data,success,error){
		let request={};
		request.body=data;
		request.token='token';
		request.client='saas-manager';
		request.tenant='weixin';
		request.channel='channel';
		let re;
		$.ajax({
			'type':'POST',
			'url':url,
			'async':false,
			'dataType':'json',
			'contentType':'application/json',
			'data':JSON.stringify(request),
			'success':
				function(data){
					let info={};
					switch (data.result){
						case 'success':
							message.info(data.message);
							re=data.body;
							break;
						case 'invalid':
							console.info(data);
							info.message=data.message;
							info.description=<div>{data.invalidInfos.map(invalidInfo=>invalidInfo.parameterName+invalidInfo.message).join('\r\n')}</div>;
							info.duration=0;
							notification.error(info);

							break;
						case 'fail':
							info.message=data.message;
							info.description=data.exception;
							info.duration=0;
							notification.error(info);
							break;
					}
					if(success){
						success(data);
					}

					},
			'error':
				function(data){
					const info={};
					info.message='调用异常';
					info.description=data;
					info.duration=0;
					notification.error(info);
					if(error){
						error(data);
					}
				}
		});
		return re;
	},
	put(url,data,success,error){
		let request={};
		request.body=data;
		request.token='token';
		request.client='saas-manager';
		request.tenant='weixin';
		request.channel='channel';
		let re;
		$.ajax({
			'type':'PUT',
			'url':url,
			'async':false,
			'dataType':'json',
			'contentType':'application/json',
			'data':JSON.stringify(request),
			'success':
				function(data){
					let info={};
					switch (data.result){
						case 'success':
							message.info(data.message);
							re=data.body;
							break;
						case 'invalid':
							info.message=data.message;
							info.description=data.invalidInfos.map(invalidInfo=>invalidInfo.parameterName+invalidInfo.message).join('\r\n');
							info.duration=0;
							notification.error(info);
							break;
						case 'fail':
							info.message=data.message;
							info.description=data.exception;
							info.duration=0;
							notification.error(info);
							break;
					}
					if(success){
						success(data);
					}
					},
			'error':
				function(data){
					const info={};
					info.message='调用异常';
					info.description=data;
					info.duration=0;
					notification.error(info);
					if(error){
						error(data);
					}
				}
		});
		return re;
	},
	get(url,data,success,error){
		let re;
		$.ajax({
			'type':'GET',
			'url':url,
			'async':false,
			'dataType':'json',
			'success':
				function(data){
					let info={};
					switch (data.result){
						case 'success':
							re=data.body;
							break;
						case 'invalid':
							info.message=data.message;
							info.description=data.invalidInfos.join('<br/>');
							info.duration=0;
							notification.error(info);
							break;
						case 'fail':
							info.message=data.message;
							info.description=data.exception;
							info.duration=0;
							notification.error(info);
							break;
					}
					if(success){
						success(data);
					}
					},
			'error':
				function(data){
					const info={};
					info.message='调用异常';
					info.description=data;
					info.duration=0;
					notification.error(info);
					if(error){
						error(data);
					}
				}
		});
		return re;
	},
	deleteAll(url,ids,success,error){
		let re;
		const idStrings=ids.map((id)=>{return ('ids='+encodeURI(id))});
		const idsString=idStrings.join('&');
		$.ajax({
			'type':'DELETE',
			'url':url+'?'+idsString ,
			'async':false,
			'dataType':'json',
			'success':
				function(data){
					let info={};
					switch (data.result){
						case 'success':
							message.info(data.message);
							re=data.body;
							break;
						case 'invalid':
							info.message=data.message;
							info.description=data.invalidInfos.join('<br/>');
							info.duration=0;
							notification.error(info);
							break;
						case 'fail':
							info.message=data.message;
							info.description=data.exception;
							info.duration=0;
							notification.error(info);
							break;
					}
					if(success){
						success(data);
					}
					},
			'error':
				function(data){
					const info={};
					info.message='调用异常';
					info.description=data;
					info.duration=0;
					notification.error(info);
					if(error){
						error(data);
					}
				}
		});
		return re;
	}
}

function Saas(url){
	this.saasUrl=url;
	this.fields=[];
	$.ajax({
		'type':'GET',
		'url':url+'/GET',
		'async':false,
		'dataType':'json',
		'success':function(data){
				const body=data.responseFields.find(
					responsefield=>{if(responsefield != undefined && responsefield.fieldName=='body'){return true}}
				)
				this.fields=body.innerSAASFieldInfos;
		}.bind(this),
		'error':
			function(data){
				const info={};
				info.message='调用异常';
				info.description=data;
				info.duration=0;
				notification.error(info);
			}
	});

	this.columns=function(){
		let re=[];
		this.fields.forEach(
			field=>{
				if(field.type=='java.lang.Boolean'){
					re.push({'title':field.name,'dataIndex':field.fieldName,render: text => text?'是':'否'});
					return;
				}
				if(field.type=='java.util.List<java.lang.String>'){
					re.push({'title':field.name,'dataIndex':field.fieldName,render: text => <div>{text.map(a=><div>{a}</div>)}</div>});
					return;
				}
				if(field.type.indexOf('model')>-1){
					re.push({'title':field.name,'dataIndex':field.fieldName,render: text => {
																						const children=[];
																						for(let proName in text){
																							if(typeof(text[proName])!="object"){
																							children.push(<div>{text[proName]}</div>);
																							}
																						}
																						return <div>{children}</div>}
							});
					return;
				}
				re.push({'title':field.name,'dataIndex':field.fieldName});
			})
		return re;
	},
	this.query=function(data,success,error){return saasUtil.query(this.saasUrl+"/query",data,success,error)};
	this.post=function(data,success,error){return saasUtil.post(this.saasUrl,data,success.bind(this),error)};
	this.put=function(data,success,error){return saasUtil.put(this.saasUrl,data,success.bind(this),error)}
	this.get=function(id,success,error){return saasUtil.get(this.saasUrl+'?id='+encodeURI(id),success,error)};
	this.deleteAll=function(ids,success,error){return saasUtil.deleteAll(this.saasUrl,ids,success,error)};
}





let SAASQueryForm=React.createClass({
	resetFields(e){
    	e.preventDefault();
		this.props.form.resetFields();
	},
	render(){
		const { getFieldProps } = this.props.form;
		const formItemLayout = {
								labelCol: { span: 6 },
								wrapperCol: { span: 14 },
		};
		const inputs=[];
		this.props.saas.fields.filter(field=>saasUtil.isFieldTypeSupport(field)).forEach(function(field,index){
			let input;
			switch (field.type){
			case 'java.lang.String': 
				input=<Input {...getFieldProps(field.fieldName+"$regex")}/>;
				break;
			case 'java.lang.Integer':
				input=(	<div>
							<InputNumber {...getFieldProps(field.fieldName+"$gte")}/>~
							<InputNumber {...getFieldProps(field.fieldName+"$lte")}/>
						</div>);
				break;
			case 'java.util.Date':
				input = (<div>
							<DatePicker {...getFieldProps(field.fieldName+"$gte")}  showTime format="yyyy-MM-dd HH:mm:ss"/>~
							<DatePicker {...getFieldProps(field.fieldName+"$lte")}  showTime format="yyyy-MM-dd HH:mm:ss"/>
						</div>)
				break;
			case 'java.lang.Boolean':
				input = <Checkbox {...getFieldProps(field.fieldName+"$eq")}>是</Checkbox>;
				break;
			case 'java.util.List<java.lang.String>':
				input = <Input {...getFieldProps(field.fieldName+"$regex")}/>;
				break;
			};
			inputs.push(
				<FormItem key={index} {...formItemLayout} label={field.name} labelCol={{ span: 10 }} wrapperCol={{ span: 14 }}>
					{input}
				</FormItem>
			)
		});
		const rowCount = Math.ceil(inputs.length/1);
		let rows=[]
		for(let rowNumber=0;rowNumber<=rowCount;rowNumber++){
			rows[rowNumber]=inputs.slice(rowNumber,(rowNumber+1));
		}
		return (
			<Form horizontal className="ant-advanced-search-form" ref="queryForm">
			<Row gutter={16} key='0'>
				{rows.map(function(row,index){return(<Col sm={8} key={index}>{row}</Col>)})}
			</Row>
    		<Row>
				<Col span={12} offset={12} style={{ textAlign: 'right' }} key='1'>
					<Button type="primary" onClick={this.props.query}>搜索</Button>
					<Button onClick={this.resetFields}>清除条件</Button>
				</Col>
			</Row>
			</Form>
		);
	}
});
SAASQueryForm=Form.create()(SAASQueryForm);


let SAASPostForm = React.createClass({
	render() {
		const { getFieldProps } = this.props.form;
		const formItemLayout = {
								labelCol: { span: 6 },
								wrapperCol: { span: 14 },
		};
		const inputs=[];
		this.props.saas.fields.filter(field=>saasUtil.isFieldTypeSupport(field)).forEach(function(field,index){
			let input;
			switch (field.type){
			case 'java.lang.String': 
				input=<Input {...getFieldProps(field.fieldName)}/>;
				break;
			case 'java.lang.Integer':
				input=<InputNumber {...getFieldProps(field.fieldName,{initialValue:0})}/>;
				break;
			case 'java.util.Date':
				input = <DatePicker {...getFieldProps(field.fieldName)}  showTime format="yyyy-MM-dd HH:mm:ss"/>;
				break;
			case 'java.lang.Boolean':
				input = <Checkbox {...getFieldProps(field.fieldName)}>是</Checkbox>;
				break;
			case 'java.util.List<java.lang.String>':
				input=<div><Input {...getFieldProps(field.fieldName+"$0")}/>
							<Input {...getFieldProps(field.fieldName+"$1")}/>
							<Input {...getFieldProps(field.fieldName+"$2")}/>
							<Input {...getFieldProps(field.fieldName+"$3")}/>
							<Input {...getFieldProps(field.fieldName+"$4")}/></div>;
				break;
			};
			if(field.type.indexOf('model')>-1&&field.getUrl!=undefined){
				let children = [];
				let modelSaas=new Saas(field.getUrl);
				modelSaas.query().forEach(model=>{
					let re="";
					for(let proName in model){
						if(typeof(model[proName])!="object"){
							re=re+model[proName]+"|";
						}
					}
					children.push(<Option key={model.id}>{re}</Option>);
					return "";
				});

				input=	<Select  style={{ width: '100%' }} {...getFieldProps(field.fieldName+".id")}>
							{children}
						</Select>;

			}

			if(field.fieldName.substring(0,4)=='sys_'){
				input=React.cloneElement(input,{disabled:true});
			}
			if(field.uiAttributes){
				let attributes=$.parseJSON(field.uiAttributes);
				input=React.cloneElement(input,attributes);
			}

			inputs.push(
				<FormItem key={index} {...formItemLayout} label={field.name}>
					{input}
				</FormItem>
			)
		});

		return (
			<Form horizontal>
				{inputs}
			</Form>
		);
	  },
});
SAASPostForm = Form.create()(SAASPostForm);


let SAASPutForm = React.createClass({
	render() {
		const { getFieldProps } = this.props.form;
		const formItemLayout = {
								labelCol: { span: 6 },
								wrapperCol: { span: 14 },
		};
		const data=this.props.data;
		const inputs=[];
		
		this.props.saas.fields.filter(field=>saasUtil.isFieldTypeSupport(field)).forEach(function(field,index){
			let input;
			switch (field.type){
			case 'java.lang.String': 
				input=<Input {...getFieldProps(field.fieldName,{initialValue:data[field.fieldName]})}/>;
				break;
			case 'java.lang.Integer':
				input=<InputNumber {...getFieldProps(field.fieldName,{initialValue:data[field.fieldName]})}/>;
				break;
			case 'java.util.Date':
				input = <DatePicker {...getFieldProps(field.fieldName,{initialValue:data[field.fieldName]})}  showTime format="yyyy-MM-dd HH:mm:ss"/>;
				break;
			case 'java.lang.Boolean':
				input = <Checkbox {...getFieldProps(field.fieldName,{initialValue:data[field.fieldName]})}>是</Checkbox>;
				break;
			case 'java.util.List<java.lang.String>':
				input=<div><Input {...getFieldProps(field.fieldName+"$0",{initialValue:data[field.fieldName][0]})}/>
							<Input {...getFieldProps(field.fieldName+"$1",{initialValue:data[field.fieldName][1]})}/>
							<Input {...getFieldProps(field.fieldName+"$2",{initialValue:data[field.fieldName][2]})}/>
							<Input {...getFieldProps(field.fieldName+"$3",{initialValue:data[field.fieldName][3]})}/>
							<Input {...getFieldProps(field.fieldName+"$4",{initialValue:data[field.fieldName][4]})}/></div>;
				break;
			};
			if(field.type.indexOf('model')>-1&&field.getUrl!=undefined){
				let children = [];
				let modelSaas=new Saas(field.getUrl);
				modelSaas.query().forEach(model=>{
					let re="";
					for(let proName in model){
						if(typeof(model[proName])!="object"){
							re=re+model[proName]+"|";
						}
					}
					children.push(<Option key={model.id}>{re}</Option>);
					return "";
				});

				input=	<Select style={{ width: '100%' }} {...getFieldProps(field.fieldName+".id",{initialValue:data[field.fieldName]?data[field.fieldName]['id']:undefined})}>
							{children}
						</Select>;

			}

			if(field.fieldName.substring(0,4)=='sys_'||field.fieldName=='id'){
				input=React.cloneElement(input,{disabled:true});
			}
			if(field.uiAttributes){
				let attributes=$.parseJSON(field.uiAttributes);
				input=React.cloneElement(input,attributes);
			}

			inputs.push(
				<FormItem key={index} {...formItemLayout} label={field.name}>
					{input}
				</FormItem>
			)
		});

		return (
			<Form horizontal>
				{inputs}
			</Form>
		);
	  },
});
SAASPutForm = Form.create()(SAASPutForm);


const SAASToolBar = React.createClass({
	getInitialState() {
		return {
			postLoading: false,
			postVisible: false,
			putLoading: false,
			putVisible: false,
		};
	},
	showPostModal() {
		this.setState({
		postVisible: true,
		});
	},
	showPutModal() {
		if(this.props.ids&&this.props.ids.length>0){
			this.setState({
			putVisible: true,
			});
		}
	},
	okPostModal() {
		this.setState({
			postLoading: true
		});
		this.props.saas.post(saasUtil.format(this.refs.saasPostForm.getFieldsValue()),
			function(data){
				if(data.result=='success'){
					this.cancelPostModal();
					this.props.query();
					this.refs.saasPostForm.resetFields();
				}
				this.setState({
					postLoading: false
				});
			}.bind(this)
		);
	},
	okPutModal() {
		this.setState({
			putLoading: true
		});
		this.props.saas.put(saasUtil.format(this.refs.saasPutForm.getFieldsValue()),
			function(data){
				if(data.result=='success'){
					this.cancelPutModal();
					this.props.query();
				}
				this.setState({
					putLoading: false
				});

			}.bind(this)
		);

	},
	cancelPostModal() {
		this.setState({
			postVisible: false,
		});
	},
	cancelPutModal() {
		this.setState({
			putVisible: false,
		});
	},
	deleteConfirmed(){
		this.props.saas.deleteAll(this.props.ids,
			function(data){
				if(data.result=='success'){
					this.props.query();
				}
			}.bind(this)
		);
	},
	render(){
		return (
			<div>
				<Button type="primary"  onClick={this.showPostModal}>新建</Button>
				<Modal title="新建" visible={this.state.postVisible} onCancel={this.cancelPostModal}
					footer={[<Button key="back" type="ghost" size="large" onClick={this.cancelPostModal}>返 回</Button>,
							 <Button key="submit" type="primary" size="large" loading={this.state.postLoading} onClick={this.okPostModal}>提 交</Button>,]}
				>
					<SAASPostForm ref="saasPostForm" saas={this.props.saas}/>
				</Modal>
				<Button onClick={this.showPutModal} disabled={!(this.props.ids.length==1)}>编辑</Button>
				<Modal title="新建" visible={this.state.putVisible} onCancel={this.cancelPutModal}
					footer={[<Button key="back" type="ghost" size="large" onClick={this.cancelPutModal}>返 回</Button>,
							 <Button key="submit" type="primary" size="large" loading={this.state.putLoading} onClick={this.okPutModal}>提 交</Button>,]}
				>
					<SAASPutForm ref="saasPutForm" saas={this.props.saas} ids={this.props.ids} data={this.props.saas.get(this.props.ids[0])}/>
				</Modal>
				<Popconfirm placement="topRight" title={'确定要删除'+this.props.ids.length+'条数据？'} onConfirm={this.deleteConfirmed}>
					<Button  disabled={!(this.props.ids.length>=1)}>删除</Button>
				</Popconfirm>
			</div>
		);
	}
});

const Content=React.createClass({
	getInitialState(){
		const saas=new Saas(this.props.url);
		
		const columns = saas.columns();
		const data = saas.query();
				
		const pagination = {
			total: data?data.length:0,
			showSizeChanger: true,
			onShowSizeChange(current, pageSize) {
				console.log('Current: ', current, '; PageSize: ', pageSize);
			},
			onChange(current) {
				console.log('Current: ', current);
			},};
		return {columns,data,pagination,saas, selectedRowKeys:[],selectedIds:[]};
	},
	setQueryData(queryData){
		this.state.setQueryData(queryData);
	},
	query(){
		const filters=[];
		const form={};
		let queryFields=this.refs.saasQueryForm.getFieldsValue();
		for(let field in queryFields){
			if(queryFields[field]){
				let filter={};
				let i=field.indexOf('$');
				filter.fieldName=field.substring(0,i);
				filter.operator=field.substring(i,field.length);
				filter.value=queryFields[field];
				filters.push(filter);
			}
		}
		form.filters=filters;
		const data = this.state.saas.query(form);
		this.setState({data,selectedRowKeys:[],selectedIds:[]});
	},
	onSelectChange(selectedRowKeys,selectedRows) {
		const selectedIds=selectedRows.map(row=>row.id);
		this.setState({ selectedRowKeys,selectedIds});
	},

	render(){
		const {selectedIds,selectedRowKeys} = this.state;
		const rowSelection = {
								selectedRowKeys,
								onChange: this.onSelectChange,
								};

			return(
				<div>
					<SAASQueryForm query={this.query} saas={this.state.saas} setQueryData={this.setQueryData} ref="saasQueryForm"/>
					<SAASToolBar saas={this.state.saas} ids={selectedIds} query={this.query}/>
					<Table rowSelection={rowSelection} columns={this.state.columns} dataSource={this.state.data} pagination={this.state.pagination} />
				</div>
			);
	}
});



const SAASMenu = React.createClass({
	render(){
		return (
			<Menu mode="inline" theme="dark" defaultSelectedKeys={[this.props.subMenus[0].menuItems[0].id]} defaultOpenKeys={[this.props.subMenus[0].id]}   onClick={this.props.itemClick}>
				{this.props.subMenus.map(function(subMenu){	
						return (
							<SubMenu key={subMenu.id} title={<span><Icon type={subMenu.icon} />{subMenu.title}</span>}>
								{
									subMenu.menuItems.map(function(menuItem){
										return (<Menu.Item key={menuItem.id} >{menuItem.title}</Menu.Item>)
									})
								}
							</SubMenu>);
					})
				}
			</Menu>
		);
	}
});


const SAASPage = React.createClass({
	getInitialState() {
		this.newTabIndex = 0;
		const menu =[{id:'a',title:'移动端',menuItems:[{id:'a1',title: '首页轮播图设置',saasInterfaceInfo:{url:'http://localhost:80/saas-qingjing-shop/indexScrollPicture'}}
																																								]},
					{id:'b',title:'内容管理',icon:'user',menuItems:[{id:'b1',title: '产品', saasInterfaceInfo:{url:'http://localhost:80/saas-qingjing-shop/production'}},
  		                                           {id:'b2',title: '产品类型',saasInterfaceInfo:{url:'http://localhost:80/saas-qingjing-shop/productionType'}},]},
					{id:'c',title:'订单管理',icon:'user',menuItems:[{id:'c1',title: '地址', saasInterfaceInfo:{url:'http://localhost:80/saas-qingjing-shop/address'}},
  		                                           {id:'c2',title: '优惠券',saasInterfaceInfo:{url:'http://localhost:80/saas-qingjing-shop/coupon'}},
  		                                           {id:'c3',title: '订单信息',saasInterfaceInfo:{url:'http://localhost:80/saas-qingjing-shop/orderList'}},
  		                                           {id:'c4',title: '支付信息',saasInterfaceInfo:{url:'http://localhost:80/saas-qingjing-shop/pay'}},]},
     		                                        ];

		//const panes=[{key:'0',title:'欢迎',content:<Content/>}];
		const panes=[];
		panes.push({key:menu[0].menuItems[0].id,title:menu[0].menuItems[0].title,content:<Content url={menu[0].menuItems[0].saasInterfaceInfo.url}/>});
		return {activeKey:menu[0].menuItems[0].id ,menu,panes};
	},
	onChange(activeKey) {
		this.setState({ activeKey });
	},
	onEdit(targetKey, action) {
		this[action](targetKey);
	},
	open({ key, item, keyPath }) {
    	const activeKey = key;
		const panes = this.state.panes;
		for(let i=0;i<panes.length;i++){
			if(panes[i].key==key){
				this.setState({activeKey});
				return;
			}
		}
		this.state.menu.forEach(subMenu=>{subMenu.menuItems.forEach(menuItem=>{if(menuItem.id==key){
			if(menuItem.content==undefined){
    			panes.push({ title:menuItem.title, content:<Content url={menuItem.saasInterfaceInfo.url}/>, key:menuItem.id});
			}else{
				panes.push({ title:menuItem.title, content:menuItem.content, key:menuItem.id});
			}
		}})})
    	this.setState({ panes, activeKey });
	},
	remove(targetKey) {
    	let activeKey = this.state.activeKey;
    	let lastIndex;
    	this.state.panes.forEach((pane, i) => {
			if (pane.key === targetKey) {
        		lastIndex = i - 1;
      		}
		});
		const panes = this.state.panes.filter(pane => pane.key !== targetKey);
		if (lastIndex >= 0 && activeKey === targetKey) {
			activeKey = panes[lastIndex].key;
		}
		this.setState({ panes, activeKey });
	},
	render() {
		return (
			<div className="ant-layout-aside">
				<aside className="ant-layout-sider">
					<div className="ant-layout-logo">
					</div>
					<SAASMenu subMenus={this.state.menu} itemClick={this.open}/>
				</aside>
				<div className="ant-layout-main">
					<div className="ant-layout-header">
					</div>
					<div className="ant-layout-container">
      					<div>
							<Tabs hideAdd onChange={this.onChange} activeKey={this.state.activeKey} type="editable-card" onEdit={this.onEdit}>
        						{this.state.panes.map(pane => <TabPane tab={pane.title} key={pane.key}>{pane.content}</TabPane>)}
							</Tabs>
						</div>
					</div>
				<div className="ant-layout-footer"> 壹佰金 版权所有 © 2016 </div>
			</div>
		</div>);
	}
});

ReactDOM.render(<SAASPage/>, page);


</script>
</body>
</html>