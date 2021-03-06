import React from 'react';
import { StyleSheet, Text, View, TouchableOpacity, WebView } from 'react-native';
import codePush from 'react-native-code-push';

let codePushOptions = {
	checkFrequency: codePush.CheckFrequency.ON_APP_RESUME,
	updateDialog: true,
	installMode: codePush.InstallMode.IMMEDIATE
};

class About extends React.Component {
	onButtonPress() {
		codePush.sync({
			updateDialog: true,
			installMode: codePush.InstallMode.IMMEDIATE
		});
	}

	codePushStatusDidChange(status) {
		switch (status) {
			case codePush.SyncStatus.CHECKING_FOR_UPDATE:
				console.log('Checking for updates.');
				break;
			case codePush.SyncStatus.DOWNLOADING_PACKAGE:
				console.log('Downloading package.');
				break;
			case codePush.SyncStatus.INSTALLING_UPDATE:
				console.log('Installing update.');
				break;
			case codePush.SyncStatus.UP_TO_DATE:
				console.log('Up-to-date.');
				break;
			case codePush.SyncStatus.UPDATE_INSTALLED:
				console.log('Update installed.');
				break;
		}
	}

	codePushDownloadDidProgress(progress) {
		console.log(progress.receivedBytes + ' of ' + progress.totalBytes + ' received.');
	}

	render() {
		return (
			<View style={styles.container}>
				<Text>about.Version: 1.0.66666666666</Text>
				<TouchableOpacity onPress={this.onButtonPress}>
					<Text>Check for updates</Text>
				</TouchableOpacity>
			</View>
		);
	}
}
var styles = StyleSheet.create({
	container: {
		flex: 1,
		backgroundColor: '#989890',
		justifyContent: 'center'
	},
	hello: {
		fontSize: 20,
		textAlign: 'center',
		margin: 10
	}
});

About = codePush(codePushOptions)(About);

export default About;
