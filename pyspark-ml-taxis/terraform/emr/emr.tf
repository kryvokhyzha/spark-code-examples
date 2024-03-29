resource "aws_emr_cluster" "cluster" {
  count         = length(var.names)
  name          = var.names[count.index]
  release_label = var.release
  applications  = concat(var.applications)

  ec2_attributes {
    subnet_id                         = element(var.subnet_ids, count.index)
    key_name                          = element(var.ssh_key_ids, count.index)
    emr_managed_master_security_group = aws_security_group.master.id
    emr_managed_slave_security_group  = aws_security_group.slave.id

    # additional_master_security_groups = aws_security_group.allow_ssh.id
    instance_profile = aws_iam_instance_profile.training_ec2_profile.arn
  }

  ebs_root_volume_size = "16"

  master_instance_group {
    instance_type = var.master_type
    bid_price     = var.master_bid_price
    ebs_config {
      size                 = var.master_ebs_size
      type                 = "gp2"
      volumes_per_instance = 1
    }
  }

  core_instance_group {
    instance_type  = var.worker_type
    instance_count = var.worker_count
    bid_price      = var.worker_bid_price
    ebs_config {
      size                 = var.worker_ebs_size
      type                 = "gp2"
      volumes_per_instance = 1
    }
  }

  tags = merge(
    var.tags,
    {
      "name" = element(var.names, count.index)
    },
  )

  configurations = file("emr/configuration.json")

  service_role = aws_iam_role.training_emr_service_role.arn

  depends_on = [
    aws_security_group.master,
    aws_security_group.slave,
  ]

  bootstrap_action {
    path = "s3://dimajix-training/scripts/aws/install-ml-taxis.sh"
    name = "install-jupyter"
  }
  bootstrap_action {
    path = "s3://dimajix-training/scripts/aws/install-reverse-proxy.sh"
    name = "install-reverse-proxy"
    args = ["-d", "${element(var.names, count.index)}.${var.proxy_domain}", "-u", var.proxy_user, "-p", var.proxy_password]
  }
}
