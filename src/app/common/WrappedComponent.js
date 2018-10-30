import React, { Component } from 'react';
import codePush from 'react-native-code-push';

let codePushOptions = {
	checkFrequency: codePush.CheckFrequency.ON_APP_RESUME,
	updateDialog: true,
	installMode: codePush.InstallMode.IMMEDIATE
};

// export default (WrappedComponent, data) => {
// 	class NewComponent extends Component {
// 		constructor() {
// 			super();
// 			this.state = { data: data };
// 		}

// 		checkUpdate = () => {
// 			codePush.sync({
// 				updateDialog: true,
// 				installMode: codePush.InstallMode.IMMEDIATE
// 			});
// 		};

// 		componentWillMount() {
// 			this.setState({ data: data });
// 		}

// 		render() {
// 			return <WrappedComponent data={this.state.data} />;
// 		}
// 	}

// 	return NewComponent;
// };

const WrappedComponent = (WrappedComponent, data) => {
	class NewComponent extends Component {
		constructor() {
			super();
			this.state = { data: data };
		}

		checkUpdate = () => {
			codePush.sync({
				updateDialog: true,
				installMode: codePush.InstallMode.IMMEDIATE
			});
		};

		componentWillMount() {
			this.setState({ data: data });
		}

		render() {
			return <WrappedComponent data={this.state.data} />;
		}
	}

	return NewComponent;
};

export default WrappedComponent;

// WrappedComponent = codePush(codePushOptions)(WrappedComponent);

// export default WrappedComponent;
