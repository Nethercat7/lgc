
let node={
  getCheckedNodes(obj) {
    return obj.tree.getCheckedNodes(false,false);
  },
  getCheckedKeys(obj) {
    return obj.tree.getCheckedKeys(false,false);
  },
  setCheckedNodes(obj,nodes) {
    obj.tree.setCheckedNodes(nodes);
  },
  setCheckedKeys(obj,ids) {
    obj.tree.setCheckedKeys(ids);
  }
};

export default node;
