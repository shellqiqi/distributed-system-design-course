# 分布式系统设计课程作业

## 对等节点(P节点)

- 接收来自C节点的原始操作消息，完成对应的资源转移、发起快照操作，如果是结束消息，则立刻结束程序的运行
- 接收来自其他P节点的资源转移和快照标记消息，完成相应的操作
- 将完成的快照发送给C节点

### 输入

节点名(i,j,k)、对端IP

### 输出

消息
