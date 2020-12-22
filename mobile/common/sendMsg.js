const msg = {
	send(obj, val, type) {
		obj.$refs.toast.show({
			title: val,
			type: type
		})
	},
	sendSuccess(obj,val){
		obj.$refs.toast.show({
			title: val,
			type: 'success'
		})
	},
	sendError(obj, val) {
		obj.$refs.toast.show({
			title: val,
			type: 'error'
		})
	}
}

export default msg
